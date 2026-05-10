package org.example;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class UIController {
    UserController userController;
    ProjectController projectController;
    UIView view = new UIView();
    Scanner scanner = new Scanner(System.in);
    User loggedInUser = null;
    Project focusedProject = null;
    HashMap<String, Runnable> commands = new HashMap<>();

    public UIController(UserController userController, ProjectController projectController) {
        this.userController = userController;
        this.projectController = projectController;
    }

    private void setupCommands() {
        commands.put("help",           () -> view.showMainMenu());
        commands.put("create-project", () -> createProject(loggedInUser));
        commands.put("add-activity",   () -> addActivity());
        commands.put("show-pactivities", () -> {
            view.showPersonalActivitiesHeader();
            view.showPersonalActivities(loggedInUser.getPersonalActivities());
        });
        commands.put("show-projects",   () -> view.showProjects(projectController.getProjects()));
        commands.put("register-time",  () -> timeRegistration(loggedInUser));
        commands.put("add-user",       () -> addUser());
        commands.put("assign-user",    () -> assignUserToProject());
        commands.put("show-users",     () -> showProjectUsers());
        commands.put("exit",           () -> System.exit(0));
        commands.put("show-activities", () -> {
            if (focusedProject != null) {
                view.showActivity(focusedProject);
                return;
            };
            Project selectedProject = selectProject();
            if (selectedProject == null) return;
            view.showActivity(selectedProject);
        });
        commands.put("show-allpactivities", () -> {
            view.showAllPersonalActivities(userController.getUsers());
        });
        commands.put("focus-project", () -> focusProject());
        commands.put("generate-project-report", () -> printProjectReport());
        commands.put("gpr", () -> printProjectReport());
        commands.put("edit-activity", () -> editActivity());
        commands.put("show-avaliable-users", () -> showAvaliableUsers());
        commands.put("add-pactivity", () -> addPersonalActivity(loggedInUser));
        commands.put("import-users", () -> importUsers());
        commands.put("edit-project", () -> editProject());
        commands.put("delete-activity", () -> deleteActivity());
        commands.put("delete-project",  () -> deleteProject());
    }

    private void editActivity() {
        Project selectedProject = getProject();
        if (selectedProject == null) return;
        Activity selectedActivity = selectActivity(selectedProject);
        if (selectedActivity == null) return;
        while(true){
            view.showActivityDetails(selectedActivity);
            view.showEditActivityMenu();
            Integer choice = promptInt("Choice");
            switch (choice) {
                case 1:
                    String newName = prompt("New name");
                    if (newName.isEmpty()) break;
                    if (!selectedActivity.setName(newName)) view.showError("Name already exists");
                    break;
                case 2:
                    Integer newStart = promptInt("New start week");
                    if (newStart == null || newStart < 0) break;
                    selectedActivity.setStartWeek(newStart);
                    break;
                case 3:
                    Integer newEnd = promptInt("New end week");
                    if (newEnd == null || newEnd < 0) break;
                    selectedActivity.setEndWeek(newEnd);
                    break;
                case 4:
                    Integer newBudget = promptInt("New time budget");
                    if (newBudget == null || newBudget < 0) break;
                    selectedActivity.timeBudget = newBudget;
                    break;
                case 5:
                    User selected = selectUser();
                    if (selected != null) selectedActivity.assignedUsers.add(selected);
                    break;
                case null, default:
                    return;
            }

        }

    }

    public void showAvaliableUsers() {
        view.showAvailableUsersScope();
        Integer scope = promptInt("Choice");
        if (scope == null) return;

        int currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        Integer startWeek = promptInt("Start week (leave blank for current week W" + currentWeek + ")");
        if (startWeek == null) startWeek = currentWeek;
        Integer endWeek = promptInt("End week (leave blank for W" + (startWeek + 3) + ")");
        if (endWeek == null) endWeek = startWeek + 3;
        if (endWeek < startWeek) {
            view.showError("End week must be >= start week!");
            return;
        }

        ArrayList<User> users;
        if (scope == 1) {
            HashMap<User, Integer> userBusyness = new HashMap<>();
            for (User u : userController.getUsers()) userBusyness.put(u, 0);
            for (Project p : projectController.getProjects()) {
                for (Activity a : p.getActivity()) {
                    if (a.getEndWeek() < startWeek || a.getStartWeek() > endWeek) continue;
                    for (User u : userBusyness.keySet()) {
                        if (a.getAssignedUsers().contains(u))
                            userBusyness.put(u, userBusyness.get(u) + 1);
                    }
                }
            }
            users = new ArrayList<>(userController.getUsers());
            users.sort(Comparator.comparingInt(userBusyness::get));
        } else if (scope == 2) {
            Project selectedProject = focusedProject != null ? focusedProject : selectProject();
            if (selectedProject == null) return;
            users = selectedProject.getAvailableUsersRanked(startWeek, endWeek);
        } else {
            view.showError("Invalid choice!");
            return;
        }

       view.showUserAvailability(users, startWeek, endWeek);
    }

    public void startScreen() {
        loggedInUser = login();
        setupCommands();
        view.showWelcomeMessage();
        mainMenu();
    }

    private User login() {
        view.showLogin();
        String initials = scanner.nextLine();
        User user = userController.login(initials);
        if (user == null) {
            view.showError("Initials cannot be found, try again: ");
            return login();
        }
        view.showLoginSuccess(user);
        return user;
    }

    public void mainMenu() {
        while (true) {
            view.showPrompt();
            String input = scanner.nextLine().trim().toLowerCase();
            Runnable command = commands.get(input);
            if (command != null) {
                command.run();
            } else {
                view.showError("Unknown error, write 'help' for help");
            }
        }
    }

    private String prompt(String message) {
        view.showInputPrompt(message);
        return scanner.nextLine().trim();
    }

    private Integer promptInt(String message) {
        while (true) {
            view.showInputPrompt(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) { return null; }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                view.showError("Please enter a number!");
            }
        }
    }

    private Project selectProject() {
        ArrayList<Project> projects = projectController.getProjects();
        view.showProjects(projects);
        Integer choice = promptInt("Choose project ID");
        if (choice == null) return null;
        for (Project p : projects) {
            if (p.getProjectID() == choice) {
                return p;
            }
        }
        view.showError("Project not found!");
        return null;
    }
    private void focusProject() {
        view.showUnfocusHint();
        focusedProject = selectProject();
        if (focusedProject != null)view.showFocusProject(focusedProject);
        else view.showProjectUnfocused();
    }

    private Activity selectActivity(Project project) {
        ArrayList<Activity> activity = project.getActivity();
        view.showActivity(project);
        Integer choice = promptInt("Choose activity");
        if (choice == null) return null;
        if (choice < 1 || choice > activity.size()) {
            view.showError("Activity not found!");
            return null;
        }
        return activity.get(choice-1);
    }

    private User selectUser() {
        ArrayList<User> users = userController.getUsers();
        view.showUsers(users);
        Integer choice = promptInt("Choose user");
        if (choice == null) return null;
        if (choice < 0 || choice >= users.size()) {
            view.showError("User not found!");
            return null;
        }
        return users.get(choice);
    }

    private void createProject(User loggedInUser) {
        String name = prompt("Enter project name");
        Project project = loggedInUser.createProject(projectController);
        project.setProjectName(name.isEmpty() ? String.valueOf(project.getProjectID()) : name);
        view.showProject(project);

        User lead = selectUser();
        if (lead != null) {
            project.setProjectLead(lead);
            view.showProjectLeaderSet(lead);
        }

        while(true) {
            String inp = prompt("Add activity (or press enter to stop)");
            if (inp.isEmpty()) break;
            if (project.createActivity(inp) == null) {
                view.showError("An activity with that name already exists!");
            } else {
                view.showActivityAdded();
            }
        }
    }

    private void importUsers(){
        view.showImportUsersMenu();
        Integer choice = promptInt("Choose option:");
        if (choice == null) return;
        switch (choice) {
            case 1:
                File newUserList = new File(prompt("Please enter File path"));
                userController.setUsers(userController.importUsersFromFile(newUserList));
                break;
            case 2:
                File newUserList2 = new File(prompt("Please enter File path"));
                ArrayList<User> currentUsers = userController.getUsers();
                ArrayList<User> newUsers = userController.importUsersFromFile(newUserList2);
                for (User user : newUsers) {
                    if (!currentUsers.contains(user)) {
                        currentUsers.add(user);
                    }
                }
                userController.setUsers(currentUsers);
                break;
            default:
        }
    }

    private LocalDate promptDate(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            String input = prompt(message + " (dd/MM/yyyy)");
            if (input.isEmpty()) return null;
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                view.showError("Invalid date format, please use dd/MM/yyyy");
            }
        }
    }

    private void addPersonalActivity(User loggedInUser) {
        String inp = prompt("Activity name (or press enter to cancel)");
        if (inp.isEmpty()) return;

        LocalDate startDate = promptDate("Start date");
        if (startDate == null) return;
        LocalDate endDate = promptDate("End date");
        if (endDate == null) return;

        if (endDate.isBefore(startDate)) {
            view.showError("End date cannot be before start date!");
            return;
        }

        Activity newActivity = loggedInUser.createPersonalActivity(inp, startDate, endDate);
        if (newActivity == null) {
            view.showError("An activity with that name already exists!");
        }
    }

    private void addActivity() {
        if (projectController.getProjects().isEmpty()) {
            view.showError("No projects exist!");
            return;
        }
        Project selectedProject = getProject();
        if (selectedProject == null) return;

        if (!selectedProject.canBeEditedBy(loggedInUser)) {
            view.showError("Only the project leader can add activities!");
            return;
        }

        if (!selectedProject.isUserAssigned(loggedInUser)) {
            view.showError("Only assigned users can add activities!");
            return;
        }

        view.showEnterActivityName();
        String inp = prompt("Add activity (or press enter to stop)");
        if (selectedProject.createActivity(inp) == null) {
            view.showError("An activity with that name already exists!");
        } else {
            view.showActivityAdded();
        }
    }

    private void addUser() {
        String initials = prompt("Enter initials");
        if (initials.isEmpty()) return;
        if (initials.length() != 4) {
            view.showError("Initials must be 4 letters long!");
            return;
        }
        if (!initials.matches("[a-zA-Z]+")) {
            view.showError("Initials can only contain letters!");
            return;
        }
        User newUser = userController.createUser(initials);
        view.showUserCreated(newUser);
    }

    private void timeRegistration(User loggedInUser) {
        Project selectedProject = getProject();
        if (selectedProject == null) return;

        Activity selectedActivity = selectActivity(selectedProject);
        if (selectedActivity == null) return;

        Integer hours = promptInt("Enter half hours");
        if (hours == null) return;
        try {
            selectedActivity.assignTimeUsed(loggedInUser, hours);
            view.showTimeRegistered();
        } catch (Exception e) {
            view.showError(e.getMessage());

        }

    }

    private void assignUserToProject() {
        Project selectedProject = getProject();
        if (selectedProject == null) return;

        User selectedUser = selectUser();
        if (selectedUser == null) return;

        if (selectedProject.getAssignedUsers().contains(selectedUser)) {
            view.showError("User is already assigned!");
            return;
        }
        selectedProject.assignUser(selectedUser);
        view.showUserAssigned();
    }

    private void showProjectUsers() {
        Project selectedProject = getProject();
        if (selectedProject == null) return;

        view.showProjectUsers(selectedProject);
    }

    private void editProject() {
        Project selectedProject = getProject();
        if (selectedProject == null) return;

        if (!selectedProject.canBeEditedBy(loggedInUser)) {
            view.showError("Only the project leader can edit a project!");
            return;
        }

        view.showEditProjectMenu(selectedProject);

        Integer choice = promptInt("Choice");
        if (choice == null) return;

        switch (choice) {
            case 1:
                String newName = prompt("New name (Enter to cancel)");
                if (!newName.isEmpty()) {
                    selectedProject.setProjectName(newName);
                    view.showNameUpdated();
                }
                break;
            case 2:
                User selected = selectUser();
                if (selected != null) {
                    selectedProject.setProjectLead(selected);
                    view.showProjectLeaderSet(selected);
                }
                break;
            default:
                break;
        }
    }


    private void printProjectReport() {
        Project selectedProject = getProject();
        int timeBudget = 0;
        int timeUsed = 0;
        if (selectedProject == null) return;

        for (Activity a : selectedProject.getActivity()){
            timeBudget += a.getTimeBudget();
            timeUsed += a.getTotalTimeUsed();
        }

        view.showProjectReport(selectedProject, timeBudget, timeUsed);
    }

    private Project getProject() {
        Project selectedProject = focusedProject;
        if (focusedProject == null) selectedProject = selectProject();
        if (selectedProject == null) return null;
        return selectedProject;
    }

    private void deleteActivity() {
        view.showDeleteActivityMenu();
        Integer scope = promptInt("Choice");
        if (scope == null) return;

        if (scope == 1) {
            Project selectedProject = getProject();
            if (selectedProject == null) return;

            if (!selectedProject.canBeEditedBy(loggedInUser)) {
                view.showError("Only the project leader can delete activities!");
                return;
            }

            Activity selectedActivity = selectActivity(selectedProject);
            if (selectedActivity == null) return;

            String confirm = prompt("Delete '" + selectedActivity.getName() + "'? (yes to confirm)");
            if (!confirm.equalsIgnoreCase("yes")) {
                view.showCancelled();
                return;
            }
            selectedProject.removeActivity(selectedActivity);
            view.showActivityDeleted();

        } else if (scope == 2) {
            ArrayList<Activity> personal = loggedInUser.getPersonalActivities();
            if (personal.isEmpty()) {
                view.showError("You have no personal activities!");
                return;
            }
            view.showPersonalActivities(personal);
            Integer choice = promptInt("Choose activity");
            if (choice == null) return;
            if (choice < 1 || choice > personal.size()) {
                view.showError("Activity not found!");
                return;
            }
            Activity toDelete = personal.get(choice - 1);
            String confirm = prompt("Delete '" + toDelete.getName() + "'? (yes to confirm)");
            if (!confirm.equalsIgnoreCase("yes")) {
                view.showCancelled();
                return;
            }
            loggedInUser.removePersonalActivity(toDelete);
            view.showPersonalActivityDeleted();

        } else {
            view.showError("Invalid choice!");
        }
    }

    private void deleteProject() {
        Project selectedProject = getProject();
        if (selectedProject == null) return;

        if (!selectedProject.canBeEditedBy(loggedInUser)) {
            view.showError("Only the project leader can delete a project!");
            return;
        }

        String confirm = prompt("Delete project " + selectedProject.getProjectID() + " '" + selectedProject.getProjectName() + "'? (yes to confirm)");
        if (!confirm.equalsIgnoreCase("yes")) {
            view.showCancelled();
            return;
        }

        if (focusedProject == selectedProject) focusedProject = null;
        projectController.removeProject(selectedProject);
        view.showProjectDeleted();
    }

}