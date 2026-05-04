package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class UserController {
    ArrayList<User> users = new ArrayList<User>();

    public UserController() {}

    public UserController(ArrayList<User> users) {
        this.users = users;
    }

    public List<User> getUsersFromHR(File file){
        return List.of();
    }
    public User createUser(String initals){
        User newUser = new User(initals);
        users.add(newUser);
        return newUser;
    }

    public void editUser(User user){

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> importUsersFromFile(File file){
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (!line.matches("[a-zA-Z]{4}")) {
                    continue;
                }
            }

            users.add(new User(line));
            }

        catch (IOException e) {
            throw new RuntimeException(e);
                }

        return users;
        }
    }
    public User login(String initials) {
    return users.stream()
        .filter(u -> u.getInitials().equals(initials))
        .findFirst()
        .orElse(null);
}
}
