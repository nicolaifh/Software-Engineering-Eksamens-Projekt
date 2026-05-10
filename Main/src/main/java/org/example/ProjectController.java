package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ProjectController {
    HashMap<Integer, ArrayList<Project>> projectsPerYear = new HashMap<>();

    public Project createProject(User caller) {
        assert caller != null :  "pre-condition";

        int year = Calendar.getInstance().get(Calendar.YEAR);

        int sequenceAtPre = (projectsPerYear.get(year) == null ? 0 :  projectsPerYear.get(year).size()) ;

        Project newProject = new Project();
        if (!projectsPerYear.containsKey(year)) {
            projectsPerYear.put(year, new ArrayList<>());
        }
        int sequence = projectsPerYear.get(year).size() + 1;
        int digits = Math.max(3, (int) Math.floor(Math.log10(sequence)) + 1);
        int multiplier = (int) Math.pow(10, digits);

        newProject.projectID = (year % 100) * multiplier + sequence;
        projectsPerYear.get(year).add(newProject);
        newProject.setProjectName(String.valueOf(newProject.projectID));
        assignUserToProject(newProject, caller);

        assert  projectsPerYear.containsKey(year) == true && 
                sequence == sequenceAtPre + 1 &&  
                newProject.getProjectID() == (year % 100) * multiplier + sequenceAtPre + 1 &&
                newProject.getProjectName().equals(String.valueOf(newProject.projectID)) && 
                newProject.assignedUsers.get(0) == caller :  "post-condition";

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
        assert user != null :  "pre-condition";
        ArrayList<Project> projectsAtPre = new ArrayList<Project>();
        for (ArrayList<Project> projects : projectsPerYear.values()) {
            projectsAtPre = projects;
        }

        ArrayList<Project> myProjects = new ArrayList<>();
        for (ArrayList<Project> projects : projectsPerYear.values()) {
            for (Project p : projects) {
                if (p.getAssignedUsers().contains(user)) {
                    myProjects.add(p);
                }
            }
        }

        assert  (projectsPerYear.values() == null && myProjects == null) ||
                (myProjects.stream().allMatch(p -> p.getAssignedUsers().contains(user))) &&
                (projectsAtPre.stream().filter(p ->  !p.isUserAssigned(user)).noneMatch(p -> myProjects.contains(p))) : "post-condition";

        return myProjects.toArray(new Project[0]);
    }

    public boolean removeProject(Project project) {
        for (ArrayList<Project> list : projectsPerYear.values()) {
            if (list.remove(project)) {
                for (User u : project.getAssignedUsers()) {
                    u.getAssignedProjects().remove(project);
                }
                return true;
            }
        }
        return false;
    }

    public HashMap<Integer, ArrayList<Project>> getProjectsPerYear() {
        return projectsPerYear;
    }
}
