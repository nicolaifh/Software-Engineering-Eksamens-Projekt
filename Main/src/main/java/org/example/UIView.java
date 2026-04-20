package org.example;

public class UIView {
    
    public void showLogin(){
        System.out.println("Please enter your initials:");
    }

    public void showLoginSuccess(User user){
        System.out.println("Welcome: " + user.getInitials());
    }

    public void showError(String message){
        System.out.println("Error: " + message);
    }

    public void showMainMenu(){
        System.out.println("\n1. Create Project");
        System.out.println("2. Add Activity");
        System.out.println("3. Time registration");
        System.out.println("0. Exit");
        System.out.println("Choose: ");
    }

    public void showProject(Project project) {
    System.out.println("Projekt oprettet med ID: " + project.getProjectID());
}
}
