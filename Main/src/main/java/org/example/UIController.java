package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class UIController {
    UserController userController;
    ProjectController projectController;
    UIView view = new UIView();
    Scanner scanner = new Scanner(System.in);
    User loggedInUser = null;

    public UIController(UserController userController, ProjectController projectController) {
        this.userController = userController;
        this.projectController = projectController;
    }

    public void startScreen() {
        loggedInUser = login();
        mainMenu(loggedInUser);
    }

    private void createProject(User loggedInUser) {
        Project project = loggedInUser.createProject(projectController);
        view.showProject(project);
    }

    private User login() {
        view.showLogin();
        String initials = scanner.next();

        User user = userController.login(initials);

        if (user == null) {
            view.showError("Initialer ikke fundet, prøv igen: ");
            return login();
        }

        view.showLoginSuccess(user);
        return user;
    }

    public void mainMenu(User loggedInUser) {
        while (true) {
            view.showMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createProject(loggedInUser);
                case 2 -> addAssignment(loggedInUser);
                case 3 -> timeRegistration(loggedInUser);
                case 4 -> addUser();
                case 0 -> System.exit(0);
            }
        }
    }

    private void addAssignment(User loggedInUser) {
        ArrayList<Project> projects = projectController.getProjects();
        view.showProjects(projects);

        int choice = scanner.nextInt();
        Project selectedProject = null;
        for (Project p : projects) {
            if (p.getProjectID() == choice) {
                selectedProject = p;
            }

        }
        if (selectedProject == null) {
            view.showError("Project not found!");
            return;
        }
        selectedProject.createAssignment();
        view.showAssignments(selectedProject);

        System.out.println("Assignment added!");
    }

    private void addUser() {
        view.showCreateUser();
        String initials = scanner.next();
        User newUser = userController.createUser(initials);
        System.out.println("User created: " + newUser.getInitials());
    }

    private void timeRegistration(User loggedInUser) {
    ArrayList<Project> projects = projectController.getProjects();
    view.showProjects(projects);

    int choice = scanner.nextInt();
    Project selectedProject = null;
    for (Project p : projects) {
        if (p.getProjectID() == choice) {
            selectedProject = p;
        }
    }
    if (selectedProject == null) {
        view.showError("Project not found!");
        return;
    }
    
    // Vis assignments
    view.showAssignments(selectedProject);
    int assignmentChoice = scanner.nextInt();
    Assignment selectedAssignment = selectedProject.assignments.get(assignmentChoice);
    
    // Indtast timer
    System.out.println("Enter hours:");
    int hours = scanner.nextInt();
    
    selectedAssignment.assignTimeUsed(loggedInUser, hours);
    System.out.println("Time registered!");
}
    

}
