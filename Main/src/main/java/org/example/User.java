package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User {
    String initials;
    int accessLevel = 0;
    int timeManagement;
    ArrayList<Project> assignedProjects = new ArrayList<>();
    ArrayList<Activity> personalActivities = new ArrayList<>();
    HashMap<String, Integer> timeUsed = new HashMap<>();

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

    public void assignTimeUsedForUser(int hours) {
        String today = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date());

        int exixtingTimeAtPre = timeUsed.getOrDefault(today, 0);
        int existingTime = timeUsed.getOrDefault(today, 0);
        
        if (existingTime + hours < 0) 
            throw new IllegalArgumentException("Can't have less than 0 half hours registed.");

        if (existingTime + hours > 48)
            throw new IllegalArgumentException("Can't register more than 48 half hours.");

        timeUsed.put(today, existingTime + hours); 
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
        assert project != null : "pre-condition";

        if (this.accessLevel >= 1) {
            project.setProjectLead(this);
        }

        assert  (this.accessLevel == 0 && project.projectLead != this) || 
                (this.accessLevel == 1 && project.projectLead == this) : "post-condition";
    }

    public HashMap<String, Integer> getTimeUsed() {
        return timeUsed;
    }
}
