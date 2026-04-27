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
        commands.put("add-assignment", () -> addAssignment());
        commands.put("register-time",  () -> timeRegistration(loggedInUser));
        commands.put("add-user",       () -> addUser());
        commands.put("assign-user",    () -> assignUserToProject());
        commands.put("show-users",     () -> showProjectUsers());
        commands.put("exit",           () -> System.exit(0));
    }

    public void startScreen() {
        loggedInUser = login();
        setupCommands();
        System.out.println("Skriv 'help' for at se kommandoer");
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
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();
            Runnable command = commands.get(input);
            if (command != null) {
                command.run();
            } else {
                view.showError("Ukendt kommando, skriv 'help' for hjælp");
            }
        }
    }

    private String prompt(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine().trim();
    }

    private int promptInt(String message) {
        while (true) {
            System.out.print(message + ": ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                view.showError("Indtast venligst et tal!");
            }
        }
    }

    private Project selectProject() {
        ArrayList<Project> projects = projectController.getProjects();
        view.showProjects(projects);
        int choice = promptInt("Vælg projekt ID");
        for (Project p : projects) {
            if (p.getProjectID() == choice) {
                return p;
            }
        }
        view.showError("Projekt ikke fundet!");
        return null;
    }

    private Assignment selectAssignment(Project project) {
        ArrayList<Assignment> assignments = project.getAssignments();
        view.showAssignments(project);
        int choice = promptInt("Vælg assignment");
        if (choice < 0 || choice >= assignments.size()) {
            view.showError("Assignment ikke fundet!");
            return null;
        }
        return assignments.get(choice);
    }

    private User selectUser() {
        ArrayList<User> users = userController.getUsers();
        view.showUsers(users);
        int choice = promptInt("Vælg bruger");
        if (choice < 0 || choice >= users.size()) {
            view.showError("Bruger ikke fundet!");
            return null;
        }
        return users.get(choice);
    }

    private void createProject(User loggedInUser) {
        Project project = loggedInUser.createProject(projectController);
        view.showProject(project);
    }

    private void addAssignment() {
        Project selectedProject = selectProject();
        if (selectedProject == null) return;

        selectedProject.createAssignment();
        view.showAssignmentAdded();
    }

    private void addUser() {
        String initials = prompt("Indtast initialer");
        User newUser = userController.createUser(initials);
        view.showUserCreated(newUser);
    }

    private void timeRegistration(User loggedInUser) {
        Project selectedProject = selectProject();
        if (selectedProject == null) return;

        Assignment selectedAssignment = selectAssignment(selectedProject);
        if (selectedAssignment == null) return;

        int hours = promptInt("Indtast timer");
        boolean success = selectedAssignment.assignTimeUsed(loggedInUser, hours);
        if (success) {
            view.showTimeRegistered();
        } else {
            view.showError("Kunne ikke registrere timer!");
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