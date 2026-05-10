package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String initials;
    int accessLevel = 0;
    int timeManagement;
    ArrayList<Project> assignedProjects = new ArrayList<>();
    ArrayList<Activity> personalActivities = new ArrayList<>();

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

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public ArrayList<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public Project createProject(ProjectController projectController) {
        Project newProject = projectController.createProject(this);
        this.assignedProjects.add(newProject);
        return newProject;
    }

    public Activity createPersonalActivity(String name, LocalDate startDate, LocalDate endDate) {
        for (Activity a : personalActivities) {
            if (a.getName().equals(name)) { return null; }
        }
        Activity activity = new Activity(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        personalActivities.add(activity);
        return activity;
    }

    public ArrayList<Activity> getPersonalActivities() {
        return personalActivities;
    }

    public boolean removePersonalActivity(Activity activity) {
        return personalActivities.remove(activity);
    }

    public void assignProjectManager(Project project) {
        if (this.accessLevel >= 1) {
            project.setProjectLead(this);
        }
    }
}
