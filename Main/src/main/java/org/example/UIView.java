package org.example;

import java.util.ArrayList;

public class UIView {

    public void showLogin() {
        System.out.println("Please enter your initials:");
    }

    public void showLoginSuccess(User user) {
        System.out.println("Welcome: " + user.getInitials());
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMainMenu() {
        System.out.println("\ncreate-project            Creates a project");
        System.out.println("add-activity              Adds a activity to project");
        System.out.println("register-time             Register Time");
        System.out.println("add-user                  Add a user");
        System.out.println("assign-user               Assign user to a project");
        System.out.println("show-users                Show user in a project");
        System.out.println("exit                      Exits");
        System.out.println("Choose: ");
    }

    public void showProject(Project project) {
        System.out.println("Project created with ID: " + project.getProjectID());
    }

    public void showProjects(ArrayList<Project> projects) {

        for (Project p : projects) {
            System.out.println(p.getProjectID());
        }
    }

    public void showActivity(Project project) {
        System.out.println("\nActivities for project " + project.getProjectID() + ":");
        for (int i = 0; i < project.activities.size(); i++) {
            System.out.println(i + 1 + ": " + project.activities.get(i).name + " ("
                    + project.activities.get(i).getTotalTimeUsed() + " Hours)");
        }
    }

    public void showCreateUser() {
        System.out.println("Enter initials:");
    }

    public void showUsers(ArrayList<User> users) {
        System.out.println("\nChoose a User (Enter Number):");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + ": " + users.get(i).getInitials());
        }
    }

    public void showProjectUsers(Project project) {
        System.out.println("\nUsers in project " + project.getProjectID() + ":");
        for (User u : project.getAssignedUsers()) {
            System.out.println(u.getInitials());
        }
    }

    public void showActivityAdded() {
        System.out.println("Activity added!");
    }

    public void showUserCreated(User user) {
        System.out.println("User created: " + user.getInitials());
    }

    public void showUserAssigned() {
        System.out.println("User assigned to project!");
    }

    public void showEnterHours() {
        System.out.println("Enter hours:");
    }

    public void showTimeRegistered() {
        System.out.println("Time registered!");
    }

    public void showWelcomeMessage() {
        System.out.println("Write 'help' to se commands");
    }

    public void showPrompt() {
        System.out.print("\n> ");
    }

    public void showInputPrompt(String message) {
        System.out.print(message + ": ");
    }

    public void showNumberError() {
        System.out.println("Error: Please enter a number!");
    }
    public void showEnterActivityName() {
    System.out.print("Enter name on activity: ");
}

}
