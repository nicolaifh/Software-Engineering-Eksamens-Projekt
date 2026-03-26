package steps;
import io.cucumber.java.ParameterType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Project;
import org.example.ProjectController;
import org.example.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    int dummyArraySize;
    Project newProject;

    @ParameterType("User[0-9]+|project manager|user")
    public User User(String name) {
        return new User(name);
    }

    @Given("{User} checks available users")
    public void project_manager_checks_available_users(User arg0) {

        throw new io.cucumber.java.PendingException();
    }
    @When("{User} is not assigned to any started assignments")
    public void user1_is_not_assigned_to_any_started_assignments(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("{User} should be present on a list over available users.")
    public void user1_should_be_present_on_a_list_over_available_users(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("{User} is assigned to a started assignments")
    public void userIsAssignedToAStartedAssignments(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("{User} should not be present on a list over available users.")
    public void userShouldNotBePresentOnAListOverAvailableUsers(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("that no other project exists")
    public void thatNoOtherProjectExists() {
        dummyArraySize = 0;
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

    @Given("that {int} other project exists")
    public void thatOtherProjectExists(int arg0) {
        dummyArraySize = arg0;
    }

    @And("that the year is {int}")
    public void thatTheYearIs(int arg0) {
        ProjectController projectController = new ProjectController();
        newProject = projectController.createProject(arg0,dummyArraySize);
    }

    @Given("{User} as an input")
    public void userAsAnInput(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("{User} is not assigned to project")
    public void userIsNotAssignedToProject(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("{User} is project leader")
    public void userIsProject_leader(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("assign {User} to project")
    public void assignUserToProject(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("{User} is already assigned to project")
    public void userIsAlreadyAssignedToProject(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("failed to assign {User} to project ErrorMessage: {string}")
    public void failedToAssignUserToProjectErrorMessage(User arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("a {User} creates an assignment")
    public void aUserCreatesAnAssignment(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("{User} is assigned to project")
    public void userIsAssignedToProject(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("create assignment")
    public void createAssignment() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("{User} is not assigned project")
    public void userIsNotAssignedProject(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("fail to create assignment")
    public void failToCreateAssignment() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("An int int{int}")
    public void anIntInt(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("{User} assigns time used on assignment")
    public void userAssignsTimeUsedOnAssignment(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("the time int{int} is assigned to user")
    public void theTimeIntIsAssignedToUser(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("a String {string}")
    public void aString(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("{User} assigns a String as input on an assignment")
    public void userAssignsAStringAsInputOnAnAssignment(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("no time is assigned to {User}")
    public void noTimeIsAssignedToUser(User arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
