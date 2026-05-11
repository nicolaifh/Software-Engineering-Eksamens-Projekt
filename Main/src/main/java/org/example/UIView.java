package org.example;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UIView {

    public void showLogin() {
        System.out.println("Please enter your initials:");
    }

    public void showLoginSuccess(User user) {
        System.out.println("Welcome: " + user.getInitials());
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMainMenu() {
        System.out.println("\ncreate-project                  Creates a new project");
        System.out.println("focus-project                   Focus a project, to skip any selection of project in any action taken");
        System.out.println("add-activity                    Add an activity to project");
        System.out.println("add-p-activity                  Add a personal activity");
        System.out.println("register-time                   Register Time");
        System.out.println("add-user                        Add a user");
        System.out.println("import-users                    Import users from file");
        System.out.println("assign-user                     Assign user to a project");
        System.out.println("show-users                      Show user in a project");
        System.out.println("edit-activity                   Edit activity");
        System.out.println("edit-project                    Edit project");
        System.out.println("delete-activity                 Delete a project or personal activity");
        System.out.println("delete-project                  Delete a project");
        System.out.println("show-available-users            Show available users on project or globally");
        System.out.println("show-p-activities               Show personal activities for users");
        System.out.println("show-all-p-activities           Show all personal activities for users");
        System.out.println("generate-project-report,gpr     Generate project report");
        System.out.println("exit                            Exits");
        System.out.println("Choose: ");
    }

    public void showProject(Project project) {
        System.out.println("Project created with ID: " + project.getProjectID() + " Name: " + project.getProjectName());
    }

    public void showFocusProject(Project project) {
        System.out.println("Project focused with ID: " + project.getProjectID());
    }
    public void showUnfocusHint() {
        System.out.println("Leave empty to unfocus.");
    }

    public void showProjectUnfocused() {
        System.out.println("Project unfocused.");
    }

    public void showProjects(ArrayList<Project> projects) {
        System.out.println("Project ID | Project name");
        for (Project p : projects) {
            System.out.println(p.getProjectID() + " ".repeat(11 - Integer.toString(p.getProjectID()).length()) + "| " + p.getProjectName());
        }
    }

    public void showEditProjectMenu(Project project) {
        System.out.println("Project: " + project.getProjectID() + " Name: " + project.getProjectName());
        System.out.println("1: Change name");
        System.out.println("2: Set project leader");
    }
    public void showNameUpdated() {
        System.out.println("Name updated.");
    }
    public void showProjectReport(Project project, int timeBudget, int timeUsed) {
        System.out.println("=".repeat(40));
        System.out.println("Project ID:   " + project.getProjectID());
        System.out.println("Project Name: " + project.getProjectName());
        System.out.println("Time Budget: " + timeBudget);
        System.out.println("Time Used/Time Left: " + timeUsed + "/" + (timeBudget - timeUsed));
        System.out.println("-".repeat(40));
        System.out.println("Users:");
        for (User u : project.getAssignedUsers()) {
            System.out.println("  - " + u.getInitials());
        }
        System.out.println("-".repeat(40));
        System.out.println("Activities:");
        for (Activity a : project.getActivity()) {
            String name = (a.getName() != null && !a.getName().isEmpty()) ? a.getName() : String.valueOf(project.getProjectID());
            System.out.println("  - " + name + " | Start: W" + a.getStartWeek() + " | End: W" + a.getEndWeek() + " | Budget: " + a.getTimeBudget() + " | Used: " + a.getTotalTimeUsed());
        }
        System.out.println("=".repeat(40));
    }
    public void showActivity(Project project) {
        System.out.println("\nActivities for project " + project.getProjectID() + ":");
        for (int i = 0; i < project.activities.size(); i++) {
            System.out.println(i + 1 + ": " + project.activities.get(i).name + " ("
                    + project.activities.get(i).getTotalTimeUsed() + " Half hours)");
        }
    }
    public void showPersonalActivitiesHeader() {
        System.out.println("\nYour personal activities:");
    }

    public void showAllPersonalActivities(ArrayList<User> users) {
        for (User u : users) {
            if (!u.getPersonalActivities().isEmpty()) {
                System.out.println("\n" + u.getInitials() + ":");
                showPersonalActivities(u.getPersonalActivities());
            }
        }
    }
    public void showDeleteActivityMenu() {
        System.out.println("Delete from:");
        System.out.println("1: Project activity");
        System.out.println("2: My personal activities");
    }

    public void showCancelled() {
        System.out.println("Cancelled.");
    }

    public void showActivityDeleted() {
        System.out.println("Activity deleted.");
    }

    public void showPersonalActivityDeleted() {
        System.out.println("Personal activity deleted.");
    }
    public void showProjectDeleted(){
        System.out.println("Project deleted");
    }

    public void showCreateUser() {
        System.out.println("Enter initials:");
    }

    public void showUsers(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + ": " + users.get(i).getInitials());
        }
    }

    public void showProjectUsers(Project project) {
        System.out.println("\nUsers in project " + project.getProjectID() + ":");
        for (User u : project.getAssignedUsers()) {
            System.out.println(u.getInitials());
        }
    }
    public void showProjectLeaderSet(User user) {
        System.out.println("Project leader set to: " + user.getInitials());
    }


    public void showActivityAdded() {
        System.out.println("Activity added!");
    }
    public void showActivityDetails(Activity activity) {
        System.out.println(activity.getName() + " | Start: W" + activity.getStartWeek() + " | End: W" + activity.getEndWeek() + " | Budget/Used: " + activity.getTimeBudget() + "/" + activity.getTotalTimeUsed());
    }

    public void showEditActivityMenu() {
        System.out.println("1: Change name");
        System.out.println("2: Change start week");
        System.out.println("3: Change end week");
        System.out.println("4: Change time budget");
        System.out.println("5: Assign user to activity");
        System.out.println("(leave blank to exit)");
    }

    public void showUserCreated(User user) {
        System.out.println("User created: " + user.getInitials());
    }
    public void showAvailableUsersScope() {
        System.out.println("Show users from:");
        System.out.println("1: All users");
        System.out.println("2: Project assigned users");
    }
    public void showUserAvailability(ArrayList<User> users, int startWeek, int endWeek) {
        System.out.println("=".repeat(40));
        System.out.println("User availability W" + startWeek + " - W" + endWeek);
        System.out.println("-".repeat(40));
        for (User u : users) {
            System.out.println("  " + u.getInitials());
        }
        System.out.println("=".repeat(40));
    }

    public void showUserAssigned() {
        System.out.println("User assigned to project!");
    }
    public void showImportUsersMenu() {
        System.out.println("1: Override current user list from file");
        System.out.println("2: Add users from file to current list");
    }

    public void showTimeRegistered() {
        System.out.println("Time registered!");
    }

    public void showWelcomeMessage() {
        System.out.println("Write 'help' to see commands");
    }

    public void showPrompt() {
        System.out.print("\n> ");
    }

    public void showInputPrompt(String message) {
        System.out.print(message + ": ");
    }

    public void assignUserText() { System.out.println("\nChoose a User to assign (Press Enter to exit)");}

    public void selectProjectLead() {System.out.println("\nChoose a User as project leader (Enter a Number or press Enter to leave blank):");}

    public void showEnterActivityName() {
    System.out.print("Enter name on activity: ");
}
    public void showPersonalActivities(ArrayList<Activity> activities) {
        for (int i = 0; i < activities.size(); i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println(i + 1 + ": " + activities.get(i).getName()
                    + " | " + activities.get(i).getStartDate().format(formatter)
                    + " - " + activities.get(i).getEndDate().format(formatter));
        }
    }
}
