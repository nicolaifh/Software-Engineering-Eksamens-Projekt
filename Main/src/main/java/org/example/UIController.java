package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

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
        commands.put("focus-project", () -> focusProject());
        commands.put("generate-project-report", () -> printProjectReport());
        commands.put("gpr", () -> printProjectReport());
        commands.put("edit-activity", () -> editActivity());
        commands.put("show-avaliable-users", () -> showAvaliableUsers());

    }

    private void editActivity() {
        Project selectedProject = focusedProject;
        if (focusedProject == null) selectedProject = selectProject();
        if (selectedProject == null) return;
        Activity selectedActivity = selectActivity(selectedProject);
        if (selectedActivity == null) return;
        while(true){
            System.out.println(selectedActivity.name + " | Start: W" + selectedActivity.getStartWeek() + " | End: W" + selectedActivity.getEndWeek() + " | TimeBudget/TimeUsed: " + selectedActivity.timeBudget +  "/" + selectedActivity.timeUsed);
            System.out.println("Please enter your choice(leave blank to exit):");
            System.out.println("1: Change name");
            System.out.println("2: Change start week");
            System.out.println("3: Change end week");
            System.out.println("4: Change timebudget");
            System.out.println("5: Assign User to activity");
            Integer choice = promptInt(scanner.nextLine());
            Integer newValue = null;
            switch (choice) {
                case 1:
                    String newName = scanner.nextLine();
                    if (newName.isEmpty()) break;
                    selectedActivity.name = newName;
                    break;
                case 2:
                    newValue = scanner.nextInt();
                    if (newValue < 0 || newValue == null) break;
                    selectedActivity.setStartWeek(newValue);
                    break;
                case 3:
                    newValue = scanner.nextInt();
                    if (newValue < 0 || newValue == null) break;
                    selectedActivity.setEndWeek(newValue);
                    break;
                case 4:
                    newValue = scanner.nextInt();
                    if (newValue < 0 || newValue == null) break;
                    selectedActivity.timeBudget = newValue;
                    break;
                case 5:
                    selectedActivity.assignedUsers.add(selectUser());
                    break;
                case null, default:
                    return;
            }

        }

    }

    public void showAvaliableUsers() {

        System.out.println("Show users from:");
        System.out.println("1: All users");
        System.out.println("2: Project assigned users");
        Integer scope = promptInt("Choice");
        if (scope == null) return;

        ArrayList<User> users;
        if (scope == 1) {
            users = new ArrayList<>(userController.getUsers());
        } else if (scope == 2) {
            Project selectedProject = focusedProject;
            if (focusedProject == null) selectedProject = selectProject();
            if (selectedProject == null) return;
            users = new ArrayList<>(selectedProject.getAssignedUsers());
        } else {
            view.showError("Invalid choice!");
            return;
        }


        int currentWeek = java.util.Calendar.getInstance().get(java.util.Calendar.WEEK_OF_YEAR);
        Integer startWeek = promptInt("Start week (leave blank for current week W" + currentWeek + ")");
        if (startWeek == null) startWeek = currentWeek;
        Integer duration = promptInt("Number of weeks (leave blank for 4)");
        if (duration == null) duration = 4;
        int endWeek = startWeek + duration - 1;


        ArrayList<Activity> allActivities = new ArrayList<>();
        for (Project p : projectController.getProjects()) {
            allActivities.addAll(p.getActivity());
        }


        HashMap<User, Integer> userBusyness = new HashMap<>();
        for (User u : users) {
            userBusyness.put(u, 0);
        }

        for (Activity a : allActivities) {
            if (a.getEndWeek() < startWeek || a.getStartWeek() > endWeek) continue;
            for (User u : users) {
                if (a.getAssignedUsers().contains(u)) {
                    userBusyness.put(u, userBusyness.get(u) + 1);
                }
            }
        }


        users.sort(Comparator.comparingInt(userBusyness::get));


        System.out.println("=".repeat(40));
        System.out.println("User availability W" + startWeek + " - W" + endWeek);
        System.out.println("-".repeat(40));
        for (User u : users) {
            int activities = userBusyness.get(u);
            System.out.println("  " + u.getInitials() + " | " + activities + " activities planned");
        }
        System.out.println("=".repeat(40));
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
        System.out.println("Leave empty to unfocus.");
        focusedProject = selectProject();
        if (focusedProject != null)view.showFocusProject(focusedProject);
        if(focusedProject == null) System.out.println("Project Unfocused");
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
        Project project = loggedInUser.createProject(projectController);
        view.showProject(project);

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

    private void addActivity() {
        if (projectController.getProjects().isEmpty()) {
            view.showError("No projects exist!");
            return;
        }
        Project selectedProject = focusedProject;
        if (focusedProject == null) selectedProject = selectProject();
        if (selectedProject == null) return;

        if (!canManageProject(selectedProject)) {
            view.showError("Only the project leader can add activities!");
            return;
        }

        view.showEnterActivityName();
        String inp = scanner.nextLine();
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
        Project selectedProject = focusedProject;
        if (focusedProject == null) selectedProject = selectProject();
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
        Project selectedProject = focusedProject;
        if (focusedProject == null) selectedProject = selectProject();
        if (selectedProject == null) return;

        User selectedUser = selectUser();
        if (selectedUser == null) return;

        if (selectedProject.getAssignedUsers().contains(selectedUser)) view.showError("User is already assigned!");
        selectedProject.assignUser(selectedUser);
        view.showUserAssigned();
    }

    private void showProjectUsers() {
        Project selectedProject = focusedProject;
        if (focusedProject == null) selectedProject = selectProject();
        if (selectedProject == null) return;

        view.showProjectUsers(selectedProject);
    }

    private void printProjectReport() {
        Project selectedProject = focusedProject;
        if (focusedProject == null) selectedProject = selectProject();
        if (selectedProject == null) return;

        System.out.println("=".repeat(40));
        System.out.println("Project ID:   " + selectedProject.getProjectID());
        System.out.println("Project Name: " + selectedProject.getProjectName());
        System.out.println("-".repeat(40));

        System.out.println("Users:");
        for (User u : selectedProject.getAssignedUsers()) {
            System.out.println("  - " + u.getInitials());
        }
        System.out.println("-".repeat(40));

        System.out.println("Activities:");
        for (Activity a : selectedProject.getActivity()) {
            String name = (a.getName() != null && !a.getName().isEmpty()) ? a.getName() : String.valueOf(selectedProject.getProjectID());
            System.out.println("  - " + name + " | Start: W" + a.getStartWeek() + " | End: W" + a.getEndWeek());
        }
        System.out.println("=".repeat(40));
    }

    public boolean canManageProject(Project project) {
        return project.projectLead == null || project.projectLead == loggedInUser;
    }
}