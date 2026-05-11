package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//made by Nicolai
public class Project {
    User projectLead;
    int projectID;
    String projectName;
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList<User> assignedUsers = new ArrayList<>();
    HashSet<String> activityNames = new HashSet<>();
//made by Nicolai
    public Project() {
    }
//made by Nicolai
    public void assignUser(User user) {
        this.assignedUsers.add(user);
    }
//made by Nicolai
    public Activity createActivity(String name, int startWeek, int endWeek, int timeBudget) {
        if (!activityNames.add(name)) return null;

        Activity activity = new Activity(name, startWeek, endWeek, timeBudget);
        activity.setProject(this);
        this.activities.add(activity);
        return activity;
    }
//made by Nicolai
    public Activity createActivity(String name) {
        return createActivity(name, 0, 0, 0);
    }
//made by Ingrid
    public ArrayList<User> getIdleUsers() {
        assert activities != null : "pre-condition"; 
        ArrayList<Activity> activitysAtPre1 = activities;
        ArrayList<Activity> activitysAtPre = activities;
        ArrayList<User> assignedUsersAtPre = assignedUsers;

        ArrayList<User> idleUsers = new ArrayList<>(this.assignedUsers);
        for (Activity a : this.activities) {
            if(a.hasStarted()){
                idleUsers.removeAll(a.getAssignedUsers());
            }
        }
        
        assert  (idleUsers == null && activities.stream().filter(a -> !a.hasStarted()) == null) ||
                (this.assignedUsers.stream() // no users in a started activity is in idleUsers.
                .filter(u -> activitysAtPre.stream().anyMatch(a -> a.hasStarted()))
                .noneMatch(u -> idleUsers.contains(u)) && 
                assignedUsersAtPre.stream() // all idleUsers is idle.
                .filter(u -> activitysAtPre1.stream().anyMatch(a -> !a.hasStarted()))
                .allMatch(u -> idleUsers.contains(u))) : "post-condition";

        return idleUsers;
    }
//made by Nicolai
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
            availableUsers.add(u);
        }
        return availableUsers;
    }
//made by Ingrid
    public boolean isUserAssigned(User user) {
        return this.getAssignedUsers().contains(user);
    }
//made by Nicolai
    public ArrayList<User> getAssignedUsers() {
        return assignedUsers;
    }
//made by Nicolai
    public int getProjectID() {
        return projectID;
    }
//made by Nicolai
    public User getProjectLead() {
        return projectLead;
    }
//made by Nicolai
    public void setProjectLead(User projectLead) {
        this.projectLead = projectLead;
    }
//made by Nicolai
    public String getProjectName() {
        return projectName;
    }
//made by Nicolai
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
//made by Benjamin
    public ArrayList<Activity> getActivity() {
        return activities;
    }
//made by Nicolai
    public boolean removeActivity(Activity activity) {
        return activities.remove(activity);
    }
//made by Sigurd
    public boolean canBeEditedBy(User user) {
        return projectLead == null || projectLead.equals(user);
    }

}
