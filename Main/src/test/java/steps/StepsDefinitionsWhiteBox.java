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
    

    
}
