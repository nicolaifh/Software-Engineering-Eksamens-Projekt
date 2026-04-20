package org.example;

import java.util.Scanner;

public class UIController {
    UserController userController;
    ProjectController projectController;
    UIView view = new UIView();
    Scanner scanner = new Scanner(System.in);

    public UIController(UserController userController, ProjectController projectController) {
        this.userController = userController;
        this.projectController = projectController;
    }

    public void startScreen() {
        User loggedInUser = login();
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

    public void mainMenu(User loggedInUser){
        while(true){
        view.showMainMenu();
        int choice = scanner.nextInt();

        switch (choice){
            case 1 -> createProject(loggedInUser);
            case 2 -> 
            case 0 -> System.exit(0);
        }
    }   
}

}
