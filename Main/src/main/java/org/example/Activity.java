package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Activity {
    Date startDate;
    Date endDate;
    String name;
    ArrayList<User> assignedUsers = new ArrayList<>();
    int timeBudget;
    HashMap<User, HashMap<String, Integer>> timeUsed = new HashMap<>();
    Boolean finished;
    Boolean started;
    Project project;

    public Activity(String name, Date startDate, Date endDate, int timeBudget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeBudget = timeBudget;
    }

    public Activity(String name){
        this.name=name;
    }

    public void assignUser(User user) {
        assignedUsers.add(user);
    }

    public Boolean hasStarted() {
        Calendar cal = Calendar.getInstance();
        if (startDate != null) {
            return !cal.before(startDate);
        }
        return false;
    }

    public ArrayList<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void assignTimeUsed(User user, int hours) {

        if (hours <= 0) throw new IllegalArgumentException("Can't register below 1 half hours");

        String today = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date());

        timeUsed.putIfAbsent(user, new HashMap<>());

        int existingTime = timeUsed.get(user).getOrDefault(today, 0);
        if (existingTime + hours > 48) {
            throw new IllegalArgumentException("Can't register more than 48 half hours");
        }
        timeUsed.get(user).put(today, existingTime + hours);
    }

    public int getTotalTimeUsed() {
    
    int total = 0;
    for (HashMap<String, Integer> dayMap : timeUsed.values()) {
        for (int time : dayMap.values()) {
            total += time;
        }
    }
    return total;
}

    public HashMap<User, HashMap<String, Integer>> getTimeUsed() {
        return timeUsed;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTimeBudget() {
        return timeBudget;
    }
    public void setTimeBudget(int timeBudget) {
        this.timeBudget = timeBudget;
    }
   
    public void setTimeUsed(HashMap<User, HashMap<String, Integer>> timeUsed) {
        this.timeUsed = timeUsed;
    }

    public Project getProject() { return project; }

    public void setProject(Project project) { this.project = project; }

    public boolean isPersonal() { return project == null; }
}
