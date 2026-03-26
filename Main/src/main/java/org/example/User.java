package org.example;

public class User {
    String initials;
    int accessLevel = 0;
    int timeManagement;

    public User(String initials) {
        this.initials = initials;
    }

    public User(String fName, String lName) {
        this.initials = fName.toLowerCase().substring(0,2) + lName.toLowerCase().substring(0,2);
    }

    public User(String initials, int accessLevel, int timeManagement) {
        this.initials = initials;
        this.accessLevel = accessLevel;
        this.timeManagement = timeManagement;
    }

    public User() {
        this.initials = "TEST";
    }

    public void assignProjectManager(){

    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getTimeMangement() {
        return timeManagement;
    }

    public void setTimeMangement(int timeMangement) {
        this.timeManagement = timeMangement;
    }
}
