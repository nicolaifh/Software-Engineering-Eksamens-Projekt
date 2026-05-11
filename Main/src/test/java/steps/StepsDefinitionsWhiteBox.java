package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.example.Activity;
import org.example.Project;
import org.example.ProjectController;
import org.example.User;
import org.example.UserController;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//made by Ingrid
public class StepsDefinitionsWhiteBox {
    ProjectController dummyProjectController = new ProjectController(); 
    UserController dummyUserController = new UserController(); 
    int dummyArraySize; 
    Project  dummyProject; 
    User dummyUser; 
    Activity dummyActivity; 
    Project newProject; 
    Calendar calendar; 
    Object dummyData; 
    Object dummyData2; 
    int dummyYear = Calendar.getInstance().get(Calendar.YEAR);
    int projectsAssined;
    Boolean dummyBool;
    String exeptionString;
//made by Ingrid
    // First whiteBox test
    @Given("that no projects in the year {int} exist")
    public void that_no_projects_in_the_current_year_exist(Integer int1) {
        assertEquals(dummyProjectController.getProjectsPerYear().containsKey(int1), false);
    }
//made by Ingrid
    @Given("the current year is {int}")
    public void the_current_year_is(Integer int1) {
        assertEquals(int1, dummyYear); 
    }
//made by Ingrid
    @When("{User} creates a project")
    public void user1_creates_a_project(User user) {
        newProject = dummyProjectController.createProject(user);
    }
//made by Ingrid
    @Then("ProjectsPerYear contains {int} as key and value [{string}]")
    public void projects_per_year_contains_as_key_and_value(Integer int1, String string) {
        assertEquals(dummyProjectController.getProjectsPerYear().containsKey(int1), true);
        assertEquals(Integer.parseInt(string), newProject.getProjectID());
    }

//made by Ingrid
    @Given("a project in the year {int} exist") 
    public void a_project_in_the_current_year_exist(int int1) {
        dummyProject = dummyProjectController.createProject(int1, dummyArraySize);
        assertEquals(dummyProjectController.getProjectsPerYear().containsKey(int1), true);
    }
//made by Ingrid
    @Then("ProjectsPerYear contains {int} as key and value [{string}, {string}]")
    public void projects_per_year_contains_current_year_as_key_and_value(Integer int1, String string, String string2) {
        assertEquals(dummyProjectController.getProjectsPerYear().containsKey(int1), true); 
        assertEquals(Integer.parseInt(string), dummyProject.getProjectID());
        assertEquals(Integer.parseInt(string2), newProject.getProjectID());
    }

//made by Ingrid
    // Second whiteBox test
    @Given("a {User} that is assigned to {int} projects")
    public void a_user_that_is_assigned_to_projects(User user, Integer int1) {
        dummyUser = user;
        projectsAssined = int1; 
        for (int i = 0; i < projectsAssined; i++) {
            dummyProjectController.createProject(user);
        }
    }
//made by Ingrid
    @Then("return empty arrayList of projects")
    public void return_empty_array_list_of_projects() {
        Project[] dummyProjects = new Project[projectsAssined]; 
        assertEquals(dummyProjects.length, dummyProjectController.getMyProject(dummyUser).length);
    }
//made by Ingrid
    @Then("return arrayList with assigned project")
    public void return_array_list_with_assigned_project() {
        Project[] dummyProjects = new Project[projectsAssined]; 
        assertEquals(dummyProjects.length, dummyProjectController.getMyProject(dummyUser).length);
    }

//made by Ingrid
    // Thrid whiteBox test
    @Given("the only {User} that is assighed to the only activity in the project")
    public void the_only_user_that_is_assighed_to_the_only_Activity_in_the_project(User user) {
        dummyProject = dummyProjectController.createProject(user);
        dummyActivity = dummyProject.createActivity("testActivity");
        dummyActivity.assignUser(user);
    }
//made by Ingrid
    @Given("the activity is startet")
    public void the_Activity_is_startet() {
        Calendar cal = Calendar.getInstance();
        dummyActivity.setStartWeek(cal.get(Calendar.WEEK_OF_YEAR) - 2);
    }
//made by Ingrid
    @Then("return empty arrayList of available useres") 
    public void return_empty_array_list_of_available_useres() { 
        assertEquals(0, dummyProject.getIdleUsers().size()); 
    }
//made by Ingrid
    @Given("the activity is not startet")
    public void the_Activity_is_not_startet() {
        Calendar cal = Calendar.getInstance();
        dummyActivity.setStartWeek(cal.get(Calendar.WEEK_OF_YEAR) + 2);
    }
//made by Ingrid
    @Then("return arrayList containing {User}")
    public void return_array_list_containing_said_user(User user) {
        assertTrue(dummyProject.getIdleUsers().contains(user)); 
    }
    
//made by Ingrid
    // Fourth whiteBox test
    @Given("a {User} with the acceslevel {int}")
    public void a_user1_with_the_acceslevel(User user, Integer int1) {
        dummyUser = user;
        dummyUser.setAccessLevel(int1); 
    }
//made by Ingrid
    @Given("a project whitout a projectLead")
    public void a_project_whitout_a_project_lead() {
        dummyProject = dummyProjectController.createProject(dummyYear, dummyArraySize);
    }
//made by Ingrid
    @When("User1 is assgned projectLead") 
    public void user1_is_assgned_project_lead() {
        dummyUser.assignProjectManager(dummyProject);  
    }
//made by Ingrid
    @Then("the project reamins without a projectLead")
    public void the_project_reamins_without_a_project_lead() {
        assertEquals(null, dummyProject.getProjectLead());
    }
//made by Ingrid
    @Then("the project is given User1 as projectLead")
    public void the_project_is_given_uset1_as_project_lead() {
        assertEquals(dummyUser, dummyProject.getProjectLead());
    }

//made by Ingrid
    // Fifth whiteBox test
    @Given("a {User} that has {int} hours registered for an activity")
    public void a_user1_that_has_hours_registered_for_an_activity(User user, Integer int1) {
        dummyProject = dummyProjectController.createProject(user); 
        dummyActivity = dummyProject.createActivity("testActivity"); 
        dummyActivity.assignUser(user); 
        dummyActivity.assignTimeUsed(user, int1); 
    }
//made by Ingrid
    @When("{User} registers {int} hours for the activity")
    public void user1_registers_hours_for_the_activity(User user, Integer int1) {
        try {
            dummyActivity.assignTimeUsed(user, int1);  
        } catch (Exception e) { 
            exeptionString = "IllegalArgumentException";
        }     
    }
//made by Ingrid
    @Then("the time used for {User} in activity is {int} hours")
    public void the_time_used_for_user1_in_activity_is_hours(User user, Integer int1) { 
        assertEquals(int1, dummyActivity.getTimeUsed(user));  
    }
//made by Ingrid
    @Then("the exeption {string} is trown for {User} registering {int} houres")
    public void the_exeption_is_trown(String string, User user, Integer int1) {
        assertEquals(string, exeptionString);  
    }

}

