package junittests;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;

import org.example.Project;
import org.example.ProjectController;
import org.example.User;
import org.junit.Test;

import io.cucumber.java.en_old.Ac;

public class RunJUnitTest {

    @Test
    public void test1() {
        ProjectController projectControler = new ProjectController();
        User user = new User();

        Project newProject =  projectControler.createProject(user);
        System.out.println(newProject);
    }


    @Test
    public void test2() {
        ProjectController projectControler = new ProjectController();
        User user = new User();

        Project[] myProjects =  projectControler.getMyProject(user);
        System.out.println(myProjects);
    }

    @Test
    public void test3() {
        ProjectController projectControler = new ProjectController();
        Calendar calendar = Calendar.getInstance();
        int projectsInYear;
        try {
            projectsInYear = projectControler.getProjectsPerYear().get(calendar.get(Calendar.YEAR)).size();
        } catch (Exception e) {
            projectsInYear = 0;
        }
        
        Project newProject = projectControler.createProject(calendar.get(Calendar.YEAR), projectsInYear);
        newProject.createActivity("newActivity");
        
        ArrayList<User> idleUsers =  newProject.getIdleUsers();
        System.out.println(idleUsers);
    }

    @Test
    public void test4() {
        assertEquals(2, 1+1);
    }

    @Test
    public void test5() {
        assertEquals(2, 1+1);
    }






}
