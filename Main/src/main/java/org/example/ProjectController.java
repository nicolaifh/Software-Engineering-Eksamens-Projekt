package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectController {
    HashMap<Integer, ArrayList<Project>> projectsPerYear = new HashMap<>();

    public Project createProject(int year){
        Project newProject = new Project();
        if (!projectsPerYear.containsKey(year)) {
            projectsPerYear.put(year, new ArrayList<>());
        }

        for(Project p : projectsPerYear.get(year)){
            if (p.projectID <= (year+1)*1000) {
                newProject.projectID = projectsPerYear.get(year).size() + 1;
            } else  {
                newProject.projectID = (year+1)*1000;
            }
        }
        projectsPerYear.get(year).add(newProject);
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


}
