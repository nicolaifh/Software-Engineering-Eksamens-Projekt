import java.util.List;
import java.util.UUID;

public class Assignment {
    private final UUID id;
    private String name;
    private String description;
    private List<User> assignedUsers;
    private int Startdate;
    private int Enddate;
    private int TimeBudget;

    public Assignment(int id, String name, String description, int Startdate, int Enddate, int TimeBudget) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.Startdate = Startdate;
        this.Enddate = Enddate;
        this.TimeBudget = TimeBudget;
    }

    public int getTimeBudget() {
        return TimeBudget;
    }

    public void setTimeBudget(int timeBudget) {
        TimeBudget = timeBudget;
    }

    public int getStartdate() {
        return Startdate;
    }

    public void setStartdate(int startdate) {
        Startdate = startdate;
    }

    public int getEnddate() {
        return Enddate;
    }

    public void setEnddate(int enddate) {
        Enddate = enddate;
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

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
}
