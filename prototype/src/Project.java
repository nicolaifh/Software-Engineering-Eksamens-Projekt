import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {
    private final UUID id;
    private String name;
    private String description;
    private final User projectOwner;
    private List<User> assignedUsers = new ArrayList<>();
    private List<Assignment> assignments = new ArrayList<>();

    public Project (User ProjectOwner){
        this.name = "Test Project";
        this.description = "Only visible for admins";
        this.projectOwner = ProjectOwner;
        this.id = UUID.randomUUID();
    }

    public Project(String name, String description, User projectOwner) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.projectOwner = projectOwner;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getProjectOwner() {
        return projectOwner;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
