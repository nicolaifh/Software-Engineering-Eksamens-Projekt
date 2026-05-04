package org.example;

import java.util.ArrayList;
import java.util.Date;

public class Project {
    User projectLead;
    int projectID;
    String projectName;
    ArrayList<Activity> activities = new ArrayList<>();
    String startDate;
    String endDate;
    ArrayList<User> assignedUsers = new ArrayList<>();

    public Project() {
    }

    public void assignUser(User user) {
        this.assignedUsers.add(user);
    }

    public Activity createActivity(String name, Date startDate, Date endDate, int timeBudget) {
        Activity activity = new Activity(name, startDate, endDate, timeBudget);
        activity.setProject(this);
        this.activities.add(activity);
        return activity;
    }

    public Activity createActivity(String name) {
        return createActivity(name, null, null, 0);
    }

    public ArrayList<User> getAvailableUsers() {
        ArrayList<User> AvailableUsers = new ArrayList<>();
        for (Activity a : this.activities) {
            if(!a.hasStarted()){
                AvailableUsers.addAll(a.getAssignedUsers());
            }
        }
        return AvailableUsers;
    }

    public ArrayList<User> getAssignedUsers() {
        return assignedUsers;
    }

    public int getProjectID() {
        return projectID;
    }

    public User getProjectLead() {
        return projectLead;
    }

    public void setProjectLead(User projectLead) {
        this.projectLead = projectLead;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public ArrayList<Activity> getActivity() {
        return activities;
    }

}
