package org.example;


import java.io.*;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

//made by Benjamin
public class UserController {
    ArrayList<User> users = new ArrayList<>();
//made by Benjamin
    public UserController() {
    }
//made by Sigurd
    public User createUser(String initals) {
        User newUser = new User(initals);
        users.add(newUser);
        return newUser;
    }
//made by Nicolai
    public ArrayList<User> getUsers() {
        return users;
    }
//made by Nicolai
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
//made by Benjamin
    public ArrayList<User> importUsersFromFile(File file) {
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (!line.matches("[a-zA-Z]{4}")) {
                    continue;
                }
                if (users.contains(new User(line))) {
                    continue;
                }
                users.add(new User(line));
                }
            }

        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
//made by Sigurd
    public User login(String initials) {
        return users.stream()
                .filter(u -> u.getInitials().equals(initials))
                .findFirst()
                .orElse(null);
    }

}
