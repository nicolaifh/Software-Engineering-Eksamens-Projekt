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

    public Assignment(String name) {
        this.name = name;
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

    public void assignTimeUsed(User user, int time) {
        if (timeUsed == null) {
            timeUsed = new HashMap<>();
        }
        timeUsed.put(user, timeUsed.getOrDefault(user, 0) + time);
    }

    public int getTotalTimeUsed() {
        if (timeUsed == null)
            return 0;
        int total = 0;
        for (int time : timeUsed.values()) {
            total += time;
        }
        return total;
    }

    public HashMap<User, Integer> getTimeUsed() {
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
}
