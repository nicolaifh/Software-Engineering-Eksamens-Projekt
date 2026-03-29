package org.example;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;

public class Project {
    User projectLead;
    int projectID;
    String projectName;
    ArrayList<Assignment> assignments = new ArrayList<>();
    String startDate;
    String endDate;
    ArrayList<User> assignedUsers = new ArrayList<>();

    public Project() {
    }

    public void assignUser(User user) {
        this.assignedUsers.add(user);
    }

    public Assignment createAssignment() {
        this.assignments.add(new Assignment("Assignment #" + this.assignments.size()));
        return this.assignments.getLast();
    }

    public ArrayList<User> getAvailableUsers() {
        ArrayList<User> AvailableUsers = new ArrayList<>();
        for (Assignment a : this.assignments) {
            if(!a.hasStarted()){
                for(User u : a.getAssignedUsers()){
                    AvailableUsers.add(u);
                }
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

}
