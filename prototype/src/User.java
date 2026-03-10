import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private int accessLevel = 0;
    private final String password;
    private String UserName;

    public User() {
        this.name = "admin";
        this.password = "admin";
        this.accessLevel = 3;
        this.UserName = "admin";
        this.id = UUID.randomUUID();
    }

    public User( String name, int accessLevel, String password, String UserName) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.accessLevel = accessLevel;
        this.password = password;
        this.UserName = UserName;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", privilegeLevel=" + accessLevel +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
