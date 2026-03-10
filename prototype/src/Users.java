import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> Users = new ArrayList<>();

    public Users() {

    }

    public Users(List<User> Users) {
        this.Users = Users;
    }
    public List<String> getUserNames () {
        List<String> temp = new ArrayList<>();
        for (User user : Users) {
            temp.add(user.getUserName());
        }
        return temp;
    }

    public User getUser (String userName) {
        for (User user : Users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    public void addUser(User user) {
        Users.add(user);
    }

    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }
}
