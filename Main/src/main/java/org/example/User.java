package org.example;

public class User {
    String initials;
    int accessLevel;
    int timeMangement;

    public User(String initials) {
        this.initials = initials;
    }

    public User(String fName, String lName) {
        this.initials = fName.toLowerCase().substring(0,2) + lName.toLowerCase().substring(0,2);
    }

    public User(String initials, int accessLevel, int timeMangement) {
        this.initials = initials;
        this.accessLevel = accessLevel;
        this.timeMangement = timeMangement;
    }

    public void assignProjectManager(){

    }
}
