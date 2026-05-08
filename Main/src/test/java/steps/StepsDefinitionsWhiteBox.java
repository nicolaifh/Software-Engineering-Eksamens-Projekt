package steps;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.example.Assignment;
import org.example.Project;
import org.example.ProjectController;
import org.example.User;
import org.example.UserController;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionsWhiteBox {
    ProjectController dummyProjectController = new ProjectController(); 
    UserController dummyUserController = new UserController(); 
    int dummyArraySize; 
    Project  dummyProject; 
    User dummyUser; 
    Assignment dummyAssignment; 
    Project newProject; 
    Calendar calendar; 
    Object dummyData; 
    Object dummyData2; 
    int dummyYear = Calendar.getInstance().get(Calendar.YEAR);
    int projectsAssined;
    Boolean dummyBool;

    // First whiteBox test
    @Given("that no projects in the year {int} exist")
    public void that_no_projects_in_the_current_year_exist(Integer int1) {
        assertEquals(dummyProjectController.getProjectsPerYear().containsKey(int1), false);
    }
    @Given("the current year is {int}")
    public void the_current_year_is(Integer int1) {
        assertEquals(int1, dummyYear); 
    }
    @When("{User} creates a project")
    public void user1_creates_a_project(User user) {
        newProject = dummyProjectController.createProject(user);
    }
    @Then("ProjectsPerYear contains {int} as key and value [{string}]")
    public void projects_per_year_contains_as_key_and_value(Integer int1, String string) {
        assertEquals(dummyProjectController.getProjectsPerYear().containsKey(int1), true);
        assertEquals(Integer.parseInt(string), newProject.getProjectID());
    }


    @Given("a project in the year {int} exist") 
    public void a_project_in_the_current_year_exist(int int1) {
        dummyProject = dummyProjectController.createProject(int1, dummyArraySize);
        assertEquals(dummyProjectController.getProjectsPerYear().containsKey(int1), true);
    }
    @Then("ProjectsPerYear contains {int} as key and value [{string}, {string}]")
    public void projects_per_year_contains_current_year_as_key_and_value(Integer int1, String string, String string2) {
        assertEquals(dummyProjectController.getProjectsPerYear().containsKey(int1), true);
        assertEquals(Integer.parseInt(string), dummyProject.getProjectID());
        assertEquals(Integer.parseInt(string2), newProject.getProjectID());
    }

    
    // Second whiteBox test
    @Given("a {User} that is assigned to {int} projects")
    public void a_user_that_is_assigned_to_projects(User user, Integer int1) {
        dummyUser = user;
        projectsAssined = int1; 
        for (int i = 0; i < projectsAssined; i++) {
            dummyProjectController.createProject(user);
        }
    }
    @Then("return empty arrayList of projects")
    public void return_empty_array_list_of_projects() {
        Project[] dummyProjects = new Project[projectsAssined]; 
        assertEquals(dummyProjects.length, dummyProjectController.getMyProject(dummyUser).length);
    }

    @Then("return arrayList with assigned project")
    public void return_array_list_with_assigned_project() {
        Project[] dummyProjects = new Project[projectsAssined]; 
        assertEquals(dummyProjects.length, dummyProjectController.getMyProject(dummyUser).length);
    }


    // Thrid whiteBox test
    @Given("the only {User} that is assighed to the only assignment in the project")
    public void the_only_user_that_is_assighed_to_the_only_assignment_in_the_project(User user) {
        dummyProject = dummyProjectController.createProject(user);
        dummyAssignment = dummyProject.createAssignment("testAssignment");
        dummyAssignment.assignUser(user);
    }
    @Given("the assignment is startet")
    public void the_assignment_is_startet() {
        calendar = Calendar.getInstance(); 
        calendar.add(Calendar.DATE, -1); 
        dummyAssignment.setStartDate(calendar.getTime()); 
    }
    @Then("return empty arrayList of available useres")
    public void return_empty_array_list_of_available_useres() { 
        assertEquals(0, dummyProject.getAvailableUsers().size()); 
    }

    @Given("the assignment is not startet")
    public void the_assignment_is_not_startet() {
        // Don't assign startDate
    }
    @Then("return arrayList containing said user")
    public void return_array_list_containing_said_user() {
        assertEquals(1, dummyProject.getAvailableUsers().size()); 
    }
    

    // Fourth whiteBox test
    @Given("a {User} with the acceslevel {int}")
    public void a_user1_with_the_acceslevel(User user, Integer int1) {
        dummyUser = user;
        dummyUser.setAccessLevel(int1); 
    }
    @Given("a project whitout a projectLead")
    public void a_project_whitout_a_project_lead() {
        dummyProject = dummyProjectController.createProject(dummyYear, dummyArraySize);
    }
    @When("User1 is assgned projectLead") 
    public void user1_is_assgned_project_lead() {
        dummyUser.assignProjectManager(dummyProject);  
    }
    @Then("the project reamins without a projectLead")
    public void the_project_reamins_without_a_project_lead() {
        assertEquals(null, dummyProject.getProjectLead());
    }

    @Then("the project is given User1 as projectLead")
    public void the_project_is_given_uset1_as_project_lead() {
        assertEquals(dummyUser, dummyProject.getProjectLead());
    }


    // Fifth whiteBox test
    @Given("an assignment whitout a startDate")
    public void an_assignment_whitout_a_start_date() {
        dummyProject = dummyProjectController.createProject(dummyYear, dummyArraySize);
        dummyAssignment = dummyProject.createAssignment("testAssignment");
        // Don't assign startDate
    }
    @When("a user checks if the assignment has started")
    public void a_user_checks_if_the_assignment_has_started() {
        dummyBool = dummyAssignment.hasStarted();
    }
    @Then("return {string}")
    public void return_bool(String string) {
        assertEquals(Boolean.parseBoolean(string), dummyBool); 
    }

    @Given("an assignment whit a startDate before the curent date")
    public void an_assignment_whit_a_start_date_before_the_curent_date() {
        dummyProject = dummyProjectController.createProject(dummyYear, dummyArraySize);
        dummyAssignment = dummyProject.createAssignment("testAssignment");
        calendar = Calendar.getInstance(); 
        dummyAssignment.setStartDate(calendar.getTime()); 
    }

    @Given("an assignment whit a startDate after the curent date")
    public void an_assignment_whit_a_start_date_after_the_curent_date() {
        dummyProject = dummyProjectController.createProject(dummyYear, dummyArraySize);
        dummyAssignment = dummyProject.createAssignment("testAssignment");
        calendar = Calendar.getInstance(); 
        calendar.add(Calendar.DATE, 1);
        dummyAssignment.setStartDate(calendar.getTime()); 
    }

}

