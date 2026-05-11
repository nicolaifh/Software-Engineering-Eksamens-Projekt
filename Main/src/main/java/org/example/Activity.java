package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Activity { 
    int startWeek;
    int endWeek;
    LocalDate startDate;
    LocalDate endDate;
    String name;
    ArrayList<User> assignedUsers = new ArrayList<>();
    int timeBudget;
    HashMap<User, HashMap<String, Integer>> timeUsed = new HashMap<>();
    Project project;


    public Activity(String name, int startWeek, int endWeek, int timeBudget) {
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
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
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        return currentWeek >= startWeek;
    }

    public ArrayList<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void assignTimeUsed(User user, int hours) {
        assert user != null && (hours >= -48 || hours <= 48) : "pre-conditon"; 
        
        String today = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date());

        timeUsed.putIfAbsent(user, new HashMap<>());

        int exixtingTimeAtPre = timeUsed.get(user).getOrDefault(today, 0);
        int existingTime = timeUsed.get(user).getOrDefault(today, 0);
        
        if (existingTime + hours < 0) 
            throw new IllegalArgumentException("Can't have less than 0 half hours registed.");

        if (existingTime + hours > 48)
            throw new IllegalArgumentException("Can't register more than 48 half hours.");
    
        timeUsed.get(user).put(today, existingTime + hours); 

        assert  (timeUsed.get(user).get(today) == exixtingTimeAtPre + hours) && 
                (timeUsed.get(user).get(today) <= 48) && 
                (timeUsed.get(user).get(today) >= 0) : "post-condition";
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

    public Integer getTimeUsed(User user) { 
        String today = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date());
        return timeUsed.get(user).get(today); 
    }

    public int getStartWeek() { return startWeek; }
    public void setStartWeek(int startWeek) { this.startWeek = startWeek; }

    public int getEndWeek() { return endWeek; }
    public void setEndWeek(int endWeek) { this.endWeek = endWeek; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getName() {
        return name;
    }
    public boolean setName(String name) {
        if (!project.activityNames.add(name)) return false;
        project.activityNames.remove(this.name);
        this.name = name;
        return true;
    }
    public int getTimeBudget() {
        return timeBudget;
    }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
