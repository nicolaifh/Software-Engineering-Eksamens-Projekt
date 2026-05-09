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


    @ParameterType("User[0-9]+|project manager|user")
    public User User(String name) {
        return new User(name);
    }



    @Given("{User} checks available users")
    public void projectManagerChecksAvailableUsers(User arg0) {
        dummyProject = dummyProjectController.createProject(arg0);
        dummyActivity = dummyProject.createActivity("activity1");
    }
    @When("{User} is not assigned to any started activitys")
    public void user1IsNotAssignedToAnyStartedActivitys(User arg0) {
        dummyProject.assignUser(arg0);
        dummyActivity.assignUser(arg0);
        Calendar cal = Calendar.getInstance();
        dummyActivity.setStartWeek(cal.get(Calendar.WEEK_OF_YEAR) + 2);

    }
    @Then("{User} should be present on a list over available users.")
    public void user1_should_be_present_on_a_list_over_available_users(User arg0) {
        assertTrue(dummyProject.getIdleUsers().contains(arg0));
    }



    @When("{User} is assigned to a started activitys")
    public void userIsAssignedToAStartedactivitys(User arg0) {
        dummyActivity.setStartWeek(dummyDate); // sets time to current time
    }

    @Then("{User} should not be present on a list over available users.")
    public void userShouldNotBePresentOnAListOverAvailableUsers(User arg0) {
        assertFalse(dummyProject.getIdleUsers().contains(arg0));
    }



    @Given("that no other project exists")
    public void thatNoOtherProjectExists() {
        dummyArraySize = 0;
    }

    @Given("that {int} other project exists")
    public void thatOtherProjectExists(int arg0) {
        dummyArraySize = arg0;
    }

    @And("that the year is {int}")
    public void thatTheYearIs(int arg0) {
        ProjectController projectController = new ProjectController();
        newProject = projectController.createProject(arg0,dummyArraySize);
    }

    @When("a {User} creates a project")
    public void aUserCreatesAProject(User arg0) {
        User user = new User();
        newProject.assignUser(user);
    }

    @Then("a project is created with the project ID {string}")
    public void aProjectIsCreatedWithTheProjectID(String arg0) {
        assertEquals(Integer.parseInt(arg0), newProject.getProjectID());
    }



    @Given("{User} as an input")
    public void userAsAnInput(User arg0) {
        dummyData = (User) arg0;
    }

    @And("{User} is project leader")
    public void userIsProject_leader(User arg0) {
        dummyData2 = (Project) arg0.createProject(dummyProjectController);
    }

    @When("{User} is not assigned to project")
    public void userIsNotAssignedToProject(User arg0) {
        assertFalse(((Project) dummyData2).getAssignedUsers().contains(arg0));
    }

    @Then("assign {User} to project")
    public void assignUserToProject(User arg0) {
        ((Project) dummyData2).assignUser(arg0);
        assertTrue(((Project) dummyData2).getAssignedUsers().contains(arg0));
    }



    @When("{User} is assigned to project")
    public void userIsAssignedToProject(User arg0) {
        assertFalse(((Project) dummyData2).getAssignedUsers().contains(arg0));
    }

    @Then("failed to assign {User} to project ErrorMessage: {string}")
    public void failedToAssignUserToProjectErrorMessage(User arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }



    @When("a {User} creates an activity")
    public void aUserCreatesAnactivity(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("create activity")
    public void createActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }



    @And("{User} is not assigned project")
    public void userIsNotAssignedProject(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("fail to create activity")
    public void failToCreateActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }



    @Given("An int {int}")
    public void anIntInt(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("{User} assigns time used on activity")
    public void userAssignsTimeUsedOnactivity(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("the time {int} is assigned to user")
    public void theTimeIntIsAssignedToUser(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("a String {string}")
    public void aString(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("{User} assigns a String as input on an activity")
    public void userAssignsAStringAsInputOnAnActivity(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("no time is assigned to {User}")
    public void noTimeIsAssignedToUser(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("a {User} wants to fetch their projects")
    public void aUserWantsToFetchTheirProjects(User arg0) {
        dummyUser = arg0;
    }

    @And("User was added to {int} projects")
    public void aUserWasAddedToProjects(int arg0) {
        for (int i = 0; i < arg0; i++){
            Project p = dummyProjectController.createProject(2026, 0);
            dummyProjectController.assignUserToProject(p,dummyUser);
        }
        assertEquals(arg0,dummyUser.getAssignedProjects().size());
    }

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
