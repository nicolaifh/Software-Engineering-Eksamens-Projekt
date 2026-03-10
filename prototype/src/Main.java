import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Project> projects = new ArrayList<>();
    public static Users Users = new Users();
    public static User currentUser;
    public static void main(String[] args) {
        Users.addUser(new User());
        projects.add(new Project(Users.getUser("admin")));
        loginMenu();
    }
    public static void mainMenu(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Choice");
        System.out.println("1. Access Project");
        System.out.println("2. Create Project");
        System.out.println("3. Create User");
        System.out.println("4. Edit User");
        System.out.println("5. Edit Project");
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 1:
                ProjectMenu();
                break;
        }
    }
    public static void loginMenu(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Enter Username");
        Scanner input = new Scanner(System.in);
        String UserName = input.nextLine();
        User selectedUser = Users.getUser(UserName);
        if (selectedUser != null) {
            System.out.println("Enter Password");
            if (input.nextLine().equals(selectedUser.getPassword())) {
                currentUser = selectedUser;
                mainMenu();
            }
            else {System.out.println("Wrong Password"); loginMenu();}
        }
        else {
            System.out.println("Invalid UserName press Enter to try again");
            if (input.nextLine().isEmpty()) loginMenu();
        }
    }
    public static void ProjectMenu(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Scanner input = new Scanner(System.in);
        int listNumber = 0;
        List<Project> projectList = new ArrayList<>();
        System.out.println("Here are your Projects:");
        for(Project p : projects){
            if(p.getAssignedUsers().contains(currentUser) || currentUser.getAccessLevel() == 3){
                listNumber++;
                projectList.add(p);
                System.out.println("["+ listNumber + "] " + p.getName() + " - " + p.getDescription());
            }
        }
        System.out.print("Enter Project ID: ");
        if (projectList.isEmpty()) {
            System.out.println("No Projects have been assigned to this User");
        }
        else if (0 >= input.nextInt() || input.nextInt() > projectList.size()) {
            System.out.println("Invalid Project ID please try again");
            ProjectMenu();
        } else  {
            projectViewMenu(projectList.get(input.nextInt() - 1));
            input.close();
        }
    }
    public static void projectViewMenu(Project project){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Scanner input = new Scanner(System.in);
        System.out.println("[1] View your assignments");
        System.out.println("[2] View all assignments");
        System.out.println("[3] View Users");
        System.out.println("[4] Add assignment");
        System.out.println("[5] Edit assignment");
        System.out.println("[6] Add User");

    }
}