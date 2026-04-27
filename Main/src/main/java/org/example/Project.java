package org.example;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;
import java.util.Date;

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

    public Assignment createAssignment(String name, Date startDate, Date endDate, int timeBudget) {
        Assignment assignment = new Assignment(name, startDate, endDate, timeBudget);
        assignment.setProject(this);
        this.assignments.add(assignment);
        return assignment;
    }

    public Assignment createAssignment(String name) {
        return createAssignment(name, null, null, 0);
    }

    public ArrayList<User> getAvailableUsers() {
        ArrayList<User> AvailableUsers = new ArrayList<>();
        for (Assignment a : this.assignments) {
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
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

}
