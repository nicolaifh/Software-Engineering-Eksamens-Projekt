package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
//made by Nicolai
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

//made by Nicolai
    public Activity(String name, int startWeek, int endWeek, int timeBudget) {
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.timeBudget = timeBudget;
    }
//made by Benjamin
    public Activity(String name){
        this.name=name;
    }
//made by Nicolai
    public void assignUser(User user) {
        assignedUsers.add(user);
    }
//made by Benjamin
    public Boolean hasStarted() {
        Calendar cal = Calendar.getInstance();
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        return currentWeek >= startWeek;
    }
//made by Nicolai
    public ArrayList<User> getAssignedUsers() {
        return assignedUsers;
    }
//made by Sigurd
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
//made by Sigurd
    public int getTotalTimeUsed() {
        int total = 0;
        for (HashMap<String, Integer> dayMap : timeUsed.values()) {
            for (int time : dayMap.values()) {
                total += time;
            }
        }
        return total;
    }
//made by Ingrid
    public Integer getTimeUsed(User user) { 
        String today = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date());
        if(timeUsed.get(user).get(today) == null) return 0;
        return timeUsed.get(user).get(today);
    }
//made by Nicolai
    public int getStartWeek() { return startWeek; }
//made by Nicolai
    public void setStartWeek(int startWeek) { this.startWeek = startWeek; }
//made by Nicolai
    public int getEndWeek() { return endWeek; }
//made by Nicolai
    public void setEndWeek(int endWeek) { this.endWeek = endWeek; }
//made by Mads
    public LocalDate getStartDate() { return startDate; }
//made by Mads
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
//made by Mads
    public LocalDate getEndDate() { return endDate; }
//made by Mads
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
//made by Nicolai
    public String getName() {
        return name;
    }
//made by Benjamin
    public boolean setName(String name) {
        if (!project.activityNames.add(name)) return false;
        project.activityNames.remove(this.name);
        this.name = name;
        return true;
    }
//made by Nicolai
    public int getTimeBudget() {
        return timeBudget;
    }
//made by Mads
    public Project getProject() { return project; }
//made by Mads
    public void setProject(Project project) { this.project = project; }
}
