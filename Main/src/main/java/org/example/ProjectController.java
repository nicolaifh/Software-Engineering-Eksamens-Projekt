package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ProjectController {
    HashMap<Integer, ArrayList<Project>> projectsPerYear = new HashMap<>();

    public Project createProject(User caller){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Project newProject = new Project();
        if (!projectsPerYear.containsKey(year)) {
            projectsPerYear.put(year, new ArrayList<>());
        }

        newProject.projectID = projectsPerYear.get(year).size() + 1;
        projectsPerYear.get(year).add(newProject);
        newProject.setProjectLead(caller);
        return newProject;
    }

    public Project createProject(int year, int dummySize){
        Project newProject = new Project();
        if (!projectsPerYear.containsKey(year)) {
            projectsPerYear.put(year, new ArrayList<>());
        }

        newProject.projectID = ((year % 100)*1000)+ dummySize + 1;
        projectsPerYear.get(year).add(newProject);
        return newProject;
    }
    public ArrayList<Project> getProjects(){
        ArrayList<Project> allProjects = new ArrayList<>();
        for (ArrayList<Project> projects : projectsPerYear.values()){
            allProjects.addAll(projects);
        }
        return allProjects;
    }

}
