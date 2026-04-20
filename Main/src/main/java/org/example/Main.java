package org.example;
public class Main {
    public static void main(String[] args) {
           UserController userController = new UserController();
        ProjectController projectController = new ProjectController();

        userController.getUsers().add(new User("huba"));
        userController.getUsers().add(new User("wilo"));

        UIController ui = new UIController(userController, projectController);
        ui.startScreen();
    }
}



    
    