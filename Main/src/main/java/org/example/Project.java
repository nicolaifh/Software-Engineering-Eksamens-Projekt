package org.example;

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

    public int getProjectID() {
        return projectID;
    }
}
