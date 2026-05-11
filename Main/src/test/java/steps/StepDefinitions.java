package steps;
import io.cucumber.java.ParameterType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
//made by Nicolai
public class StepDefinitions {
    ProjectController dummyProjectController = new ProjectController();
    UserController dummyUserController = new UserController();
    int dummyArraySize;
    Project  dummyProject;
    User dummyUser;
    Activity dummyActivity;
    Project newProject;
    Calendar calendar;
    int dummyDate = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    Object dummyData;
    Object dummyData2;
    String dummyExeption;
    int dummyInt;

    @ParameterType("User[0-9]+|project manager|user")
    public User User(String name) {
        return new User(name);
    }


//made by Nicolai
    @Given("User checks available users")
    public void projectManagerChecksAvailableUsers() {
        dummyUser = new User();
        dummyProject = new Project();
        dummyProject = dummyProjectController.createProject(dummyUser);
        dummyActivity = dummyProject.createActivity("activity1");
    }
//made by Nicolai
    @When("User is not assigned to any started activitys")
    public void user1IsNotAssignedToAnyStartedActivitys() {
        dummyProject.assignUser(dummyUser);
        dummyActivity.assignUser(dummyUser);
        Calendar cal = Calendar.getInstance();
        dummyActivity.setStartWeek(cal.get(Calendar.WEEK_OF_YEAR) + 2);

    }
//made by Nicolai
    @Then("User should be present on a list over available users.")
    public void user1_should_be_present_on_a_list_over_available_users() {
        assertTrue(dummyProject.getIdleUsers().contains(dummyUser));
    }


//made by Ingrid
    @When("User is assigned to a started activitys")
    public void userIsAssignedToAStartedactivitys() {
        dummyActivity.assignUser(dummyUser);
        dummyActivity.setStartWeek(dummyDate); // sets time to current time
    }
//made by Ingrid
    @Then("User should not be present on a list over available users.")
    public void userShouldNotBePresentOnAListOverAvailableUsers() {
        assertFalse(dummyProject.getIdleUsers().contains(dummyUser));
    }


//made by Ingrid
    @Given("that no other project exists")
    public void thatNoOtherProjectExists() {
        dummyArraySize = 0;
    }
//made by Benjamin
    @Given("that {int} other project exists")
    public void thatOtherProjectExists(int arg0) {
        dummyArraySize = arg0;
    }
//made by Benjamin
    @And("that the year is {int}")
    public void thatTheYearIs(int arg0) {
        ProjectController projectController = new ProjectController();
        newProject = projectController.createProject(arg0,dummyArraySize);
    }
//made by Benjamin
    @When("a {User} creates a project")
    public void aUserCreatesAProject(User arg0) {
        User user = new User();
        newProject.assignUser(user);
    }
//made by Benjamin
    @Then("a project is created with the project ID {string}")
    public void aProjectIsCreatedWithTheProjectID(String arg0) {
        assertEquals(Integer.parseInt(arg0), newProject.getProjectID());
    }


//made by Mads
    @Given("{User} as an input")
    public void userAsAnInput(User arg0) {
        dummyData = (User) arg0;
    }
//made by Mads
    @And("{User} is project leader")
    public void userIsProject_leader(User arg0) {
        dummyData2 = (Project) arg0.createProject(dummyProjectController);
    }
//made by Mads
    @When("{User} is not assigned to project")
    public void userIsNotAssignedToProject(User arg0) {
        assertFalse(((Project) dummyData2).getAssignedUsers().contains(arg0));
    }
//made by Mads
    @Then("assign {User} to project")
    public void assignUserToProject(User arg0) {
        dummyUser = arg0;
        ((Project) dummyData2).assignUser(dummyUser);
        assertTrue(((Project) dummyData2).getAssignedUsers().contains(arg0));
    }


//made by Sigurd
    @When("User is assigned to project")
    public void userIsAssignedToProject() {
        ((Project) dummyData2).assignUser(dummyUser);
        assertTrue(((Project) dummyData2).getAssignedUsers().contains(dummyUser));
    }
//made by Sigurd
    @Then("failed to assign user to project ErrorMessage: {string}")
    public void failedToAssignUserToProjectErrorMessage(String arg1) {
        if (((Project) dummyData2).getAssignedUsers().contains(dummyUser)) {
            dummyExeption = "User already assigned.";
        }
        assertEquals(arg1, dummyExeption);
    }


//made by Sigurd
    @When("a {User} creates an activity")
    public void aUserCreatesAnactivity(User arg0) {
        dummyUser = (User) arg0;
        dummyProject = dummyUser.createProject(dummyProjectController);
        dummyActivity = dummyProject.createActivity("testActivity");

    }
//made by Sigurd
    @When("{User} is assigned to a project")
    public void userIsAssignedToAProject(User arg0) {
        dummyProject.assignUser(arg0);
    }
//made by Nicolai
    @Then("create activity")
    public void createActivity() {
        assertEquals(dummyProject.getActivity().get(0), dummyActivity);
    }


//made by Nicolai
    @And("{User} is not assigned a project")
    public void userIsNotAssignedProject(User arg0) {
        calendar = calendar.getInstance();
        dummyProject = dummyProjectController.createProject(calendar.get(Calendar.YEAR), dummyArraySize);
        if (dummyProject.isUserAssigned(arg0)) {
            dummyActivity = dummyProject.createActivity("testActivity");
        } else {
            dummyActivity = null;
        }
    }
//made by Nicolai
    @Then("fail to create activity")
    public void failToCreateActivity() {
        assertEquals(null, dummyActivity);
    }

//made by Nicolai
    @Given("An int {int}")
    public void anIntInt(int arg0) {
        dummyInt = arg0;
    }
//made by Nicolai
    @When("{User} assigns time used on activity")
    public void userAssignsTimeUsedOnactivity(User arg0) {
        dummyUser = arg0;
        dummyProject = dummyProjectController.createProject(dummyUser);
        dummyProject.assignUser(dummyUser);
        dummyActivity = dummyProject.createActivity("testActivity");
        dummyActivity.assignUser(dummyUser);
        dummyActivity.assignTimeUsed(dummyUser, dummyInt);
    }
//made by Nicolai
    @Then("the time {int} is assigned to user")
    public void theTimeIntIsAssignedToUser(int arg0) {
        assertEquals(arg0, dummyActivity.getTimeUsed(dummyUser));
    }
//made by Nicolai
    @Given("a String {string}")
    public void aString(String arg0) {
        dummyExeption = arg0;
    }
//made by Nicolai
    @When("{User} assigns a String as input on an activity")
    public void userAssignsAStringAsInputOnAnActivity(User arg0) {
        dummyUser = arg0;
        dummyProject = dummyProjectController.createProject(dummyUser);
        dummyActivity = dummyProject.createActivity("testActivity");
        try{
            Integer.parseInt(dummyExeption);

        }   catch (Exception e) {
            dummyActivity.assignTimeUsed(arg0, 0);
        }
    }
//made by Nicolai
    @Then("no time is assigned to user")
    public void noTimeIsAssignedToUser() {
        assertEquals(0, dummyActivity.getTimeUsed(dummyUser));
    }

//made by Benjamin
    @Given("a {User} wants to fetch their projects")
    public void aUserWantsToFetchTheirProjects(User arg0) {
        dummyUser = arg0;
    }
//made by Benjamin
    @And("User was added to {int} projects")
    public void aUserWasAddedToProjects(int arg0) {
        for (int i = 0; i < arg0; i++){
            Project p = dummyProjectController.createProject(2026, 0);
            dummyProjectController.assignUserToProject(p,dummyUser);
        }
        assertEquals(arg0,dummyUser.getAssignedProjects().size());
    }
//made by Benjamin
    @Then("return {int} projects")
    public void returnProjects(int arg0) {
        int successes = 0;
        for (Project project : dummyUser.getAssignedProjects()) {
            if (project.getAssignedUsers().contains(dummyUser)) {
                successes++;
            }
        }
        assertEquals(arg0,successes);
    }

}
