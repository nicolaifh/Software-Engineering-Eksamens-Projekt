package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Project {
    User projectLead;
    int projectID;
    String projectName;
    ArrayList<Activity> activities = new ArrayList<>();
    int startWeek;
    int endWeek;
    ArrayList<User> assignedUsers = new ArrayList<>();
    HashSet<String> activityNames = new HashSet<>();

    public Project() {
    }

    public void assignUser(User user) {
        this.assignedUsers.add(user);
    }

    public Activity createActivity(String name, int startWeek, int endWeek, int timeBudget) {
        if (!activityNames.add(name)) return null;

        Activity activity = new Activity(name, startWeek, endWeek, timeBudget);
        activity.setProject(this);
        this.activities.add(activity);
        return activity;
    }

    public Activity createActivity(String name) {
        return createActivity(name, 0, 0, 0);
    }

    public ArrayList<User> getIdleUsers() {
        ArrayList<User> idleUsers = new ArrayList<>();
        for (Activity a : this.activities) {
            if(!a.hasStarted()){
                idleUsers.addAll(a.getAssignedUsers());
            }
        }
        return idleUsers;
    }

    public ArrayList<User> getAvailableUsersRanked(int startWeek, int endWeek) {
        ArrayList<User> availableUsers = new ArrayList<>();
        HashMap<User, Integer> amountOfActivities = new HashMap<>();
        for (User u :  this.assignedUsers) {
            amountOfActivities.put(u, 0);
            for (Activity a : this.activities) {
                if (a.getAssignedUsers().contains(u) && !(a.getEndWeek() < startWeek || a.getStartWeek() > endWeek)) {
                    amountOfActivities.put(u, amountOfActivities.get(u) + 1);
                }
            }
        }
        return availableUsers;
    }

    public boolean isUserAssigned(User user) {
        return this.getAssignedUsers().contains(user);
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
    public boolean removeActivity(Activity activity) {
        return activities.remove(activity);
    }


}
