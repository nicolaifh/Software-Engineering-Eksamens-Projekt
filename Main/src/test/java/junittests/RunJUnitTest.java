package junittests;
import static org.junit.Assert.assertEquals;

import org.example.Project;
import org.example.ProjectController;
import org.example.User;
import org.junit.Test;

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

        Project[] newProject =  projectControler.getMyProject(user);
        System.out.println(newProject);
    }

    @Test
    public void test3() {
        assertEquals(2, 1+1);
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
