package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserController {
    ArrayList<User> users = new ArrayList<User>();

    public UserController() {}

    public UserController(ArrayList<User> users) {
        this.users = users;
    }

    public List<User> getUsersFromHR(File file){
        return List.of();
    }

    public void editUser(User user){

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    public User login(String initials) {
    return users.stream()
        .filter(u -> u.getInitials().equals(initials))
        .findFirst()
        .orElse(null);
}
}
