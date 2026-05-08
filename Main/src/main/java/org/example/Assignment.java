package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Assignment {
    Date startDate;
    Date endDate;
    String name;
    ArrayList<User> assignedUsers = new ArrayList<>();
    int timeBudget;
    HashMap<User, Integer> timeUsed;
    Boolean finished;
    Boolean started;
    Project project;

    public Assignment(String name, Date startDate, Date endDate, int timeBudget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeBudget = timeBudget;
    }

    public Assignment(String name){
        this.name=name;
    }

    public void assignUser(User user){
        assignedUsers.add(user);
    }

    public Boolean hasStarted(){
        Calendar cal = Calendar.getInstance();
        Calendar startDateCal = Calendar.getInstance();; 
        if (startDate != null) {
            startDateCal.setTime(startDate); 
            return !cal.before(startDateCal);
        }
        return false;
    }

    public ArrayList<User> getAssignedUsers(){
        return assignedUsers;
    }

    public void assignTimeUsed(User user, int time){

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
    public HashMap<User, Integer> getTimeUsed() {
        return timeUsed;
    }
    public void setTimeUsed(HashMap<User, Integer> timeUsed) {
        this.timeUsed = timeUsed;
    }

    public Project getProject() { return project; }

    public void setProject(Project project) { this.project = project; }

    public boolean isPersonal() { return project == null; }
}
