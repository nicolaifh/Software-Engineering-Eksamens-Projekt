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
        System.out.println("\n1. Create Project");
        System.out.println("2. Add Assignment");
        System.out.println("3. Time registration");
        System.out.println("4. Create User");
        System.out.println("0. Exit");
        System.out.println("Choose: ");
    }

    public void showProject(Project project) {
        System.out.println("Projekt oprettet med ID: " + project.getProjectID());
    }

    public void showProjects(ArrayList<Project> projects) {
        System.out.println("\nVælg et projekt:");
        for (Project p : projects) {
            System.out.println(p.getProjectID());
        }
    }

    public void showAssignments(Project project) {
    System.out.println("\nAssignments for project " + project.getProjectID() + ":");
    for (int i = 0; i < project.assignments.size(); i++) {
        System.out.println(i + ": " + project.assignments.get(i).name + " (" + project.assignments.get(i).getTotalTimeUsed() + " timer)");
    }
}

    public void showCreateUser() {
        System.out.println("Enter initials:");
    }
}
