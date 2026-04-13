package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String initials;
    int accessLevel = 0;
    int timeManagement;
    ArrayList<Project> assignedProjects = new ArrayList<>();
    HashMap<Project, ArrayList<Assignment>> assignedAssignments;

    public User(String initials) {
        this.initials = initials;
    }

    public User(String fName, String lName) {
        this.initials = fName.toLowerCase().substring(0,2) + lName.toLowerCase().substring(0,2);
    }

    public User(String initials, int accessLevel, int timeManagement) {
        this.initials = initials;
        this.accessLevel = accessLevel;
        this.timeManagement = timeManagement;
    }

    public User() {
        this.initials = "TEST";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return initials.equals(user.initials);
    }

    @Override
    public int hashCode() {
        return initials.hashCode();
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getTimeMangement() {
        return timeManagement;
    }

    public void setTimeMangement(int timeMangement) {
        this.timeManagement = timeMangement;
    }

    public ArrayList<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public Project createProject(ProjectController projectController) {
        Project newProject = projectController.createProject(this);
        this.assignedProjects.add(newProject);
        return newProject;
    }

    public boolean login(String inputInitials) {
        return this.initials.equals(inputInitials);
    }

    public void assignProjectManager(Project project) {
        if (this.accessLevel >= 1) {
            project.setProjectLead(this);
        }
    }
}
