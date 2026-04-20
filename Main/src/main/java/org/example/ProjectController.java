package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ProjectController {
    HashMap<Integer, ArrayList<Project>> projectsPerYear = new HashMap<>();

    public Project createProject(User caller) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Project newProject = new Project();
        if (!projectsPerYear.containsKey(year)) {
            projectsPerYear.put(year, new ArrayList<>());
        }

        newProject.projectID = ((year % 100) * 1000) + projectsPerYear.get(year).size() + 1;
        projectsPerYear.get(year).add(newProject);
        newProject.setProjectLead(caller);
        newProject.setProjectName(String.valueOf(newProject.projectID));
        return newProject;
    }

    public Project createProject(int year, int dummySize) {
        Project newProject = new Project();
        if (!projectsPerYear.containsKey(year)) {
            projectsPerYear.put(year, new ArrayList<>());
        }

        newProject.projectID = ((year % 100) * 1000) + dummySize + 1;
        projectsPerYear.get(year).add(newProject);
        return newProject;
    }

    public void assignUserToProject(Project project, User user) {
        project.assignUser(user);
        user.getAssignedProjects().add(project);
    }

    public User[] getAvailableUsers(Project project) {
        ArrayList<User> available = project.getAvailableUsers();
        return available.toArray(new User[0]);
    }

    public Project[] getMyProject(User user) {
        ArrayList<Project> myProjects = new ArrayList<>();
        for (ArrayList<Project> projects : projectsPerYear.values()) {
            for (Project p : projects) {
                if (p.getAssignedUsers().contains(user)) {
                    myProjects.add(p);
                }
            }
        }
        return myProjects.toArray(new Project[0]);

        //public Project[] getMyProject(User user) {
        //    return projectsPerYear.values().stream()
        //        .flatMap(ArrayList::stream)
        //        .filter(p -> p.getAssignedUsers().contains(user))
        //        .toArray(Project[]::new);
        //}
    }

        public String generateProjectReport(Project project) {
            return "";
        }
}
