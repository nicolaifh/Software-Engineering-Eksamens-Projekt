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
        //newProject.setProjectLead(caller); - var det ikke meningen at den skulle kunne være ingen i starten?  Jo.
        newProject.setProjectName(String.valueOf(newProject.projectID));
        assignUserToProject(newProject, caller);
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
    public ArrayList<Project> getProjects(){
        ArrayList<Project> allProjects = new ArrayList<>();
        for (ArrayList<Project> projects : projectsPerYear.values()){
            allProjects.addAll(projects);
        }
        return allProjects;
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
    }

    public boolean removeProject(Project project) {
        for (ArrayList<Project> list : projectsPerYear.values()) {
            if (list.remove(project)) {
                // Also clean up from any assigned users
                for (User u : project.getAssignedUsers()) {
                    u.getAssignedProjects().remove(project);
                }
                return true;
            }
        }
        return false;
    }
}
