package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UIController {
    UserController userController;
    ProjectController projectController;
    UIView view = new UIView();
    Scanner scanner = new Scanner(System.in);
    User loggedInUser = null;
    HashMap<String, Runnable> commands = new HashMap<>();

    public UIController(UserController userController, ProjectController projectController) {
        this.userController = userController;
        this.projectController = projectController;
    }

    private void setupCommands() {
        commands.put("help",           () -> view.showMainMenu());
        commands.put("create-project", () -> createProject(loggedInUser));
        commands.put("show-projects",   () -> view.showProjects(projectController.getProjects()));
        commands.put("add-assignment", () -> addAssignment());
        commands.put("register-time",  () -> timeRegistration(loggedInUser));
        commands.put("add-user",       () -> addUser());
        commands.put("assign-user",    () -> assignUserToProject());
        commands.put("show-users",     () -> showProjectUsers());
        commands.put("exit",           () -> System.exit(0));
        commands.put("show-assignments", () -> {Project selectedProject = selectProject();
            if (selectedProject == null) return;
            view.showAssignments(selectedProject);
        });

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
            view.showError("Initialer ikke fundet, prøv igen:");
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

    private int promptInt(String message) {
        while (true) {
            view.showInputPrompt(message);
            String input = scanner.nextLine().trim();
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
        int choice = promptInt("Choose project ID");
        for (Project p : projects) {
            if (p.getProjectID() == choice) {
                return p;
            }
        }
        view.showError("Project not found!");
        return null;
    }

    private Assignment selectAssignment(Project project) {
        ArrayList<Assignment> assignments = project.getAssignments();
        view.showAssignments(project);
        int choice = promptInt("Choose assignment");
        if (choice < 1 || choice > assignments.size()) {
            view.showError("Assignment not found!");
            return null;
        }
        return assignments.get(choice-1);
    }

    private User selectUser() {
        ArrayList<User> users = userController.getUsers();
        view.showUsers(users);
        int choice = promptInt("Choose user");
        if (choice < 0 || choice >= users.size()) {
            view.showError("User not found!");
            return null;
        }
        return users.get(choice);
    }

    private void createProject(User loggedInUser) {
        Project project = loggedInUser.createProject(projectController);
        view.showProject(project);
    }

    private void addAssignment() {
        System.out.println("\nChoose a project:");
        Project selectedProject = selectProject();
        if (selectedProject == null) return;
        view.showEnterAssignmentName();
        
        String inp = scanner.nextLine();

        selectedProject.createAssignment(inp);
        view.showAssignmentAdded();
    }

    private void addUser() {
        String initials = prompt("Enter initials");
        User newUser = userController.createUser(initials);
        view.showUserCreated(newUser);
    }

    private void timeRegistration(User loggedInUser) {
        Project selectedProject = selectProject();
        if (selectedProject == null) return;

        Assignment selectedAssignment = selectAssignment(selectedProject);
        if (selectedAssignment == null) return;

        int hours = promptInt("Enter hours");
        boolean success = selectedAssignment.assignTimeUsed(loggedInUser, hours);
        if (success) {
            view.showTimeRegistered();
        } else {
            view.showError("Could not register hours!");
        }
    }

    private void assignUserToProject() {
        Project selectedProject = selectProject();
        if (selectedProject == null) return;

        User selectedUser = selectUser();
        if (selectedUser == null) return;

        selectedProject.assignUser(selectedUser);
        view.showUserAssigned();
    }

    private void showProjectUsers() {
        Project selectedProject = selectProject();
        if (selectedProject == null) return;

        view.showProjectUsers(selectedProject);
    }
}