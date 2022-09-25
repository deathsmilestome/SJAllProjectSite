package org.example.SpringAllProjectSite.service;

import org.example.SpringAllProjectSite.database.Project;
import org.example.SpringAllProjectSite.exception.ProjectNameAlreadyExistException;
import org.example.SpringAllProjectSite.exception.ProjectsNotFoundedException;
import org.example.SpringAllProjectSite.exception.SomeFiledsAreNullException;
import org.example.SpringAllProjectSite.repos.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public Project createProject(Project project) throws ProjectNameAlreadyExistException, SomeFiledsAreNullException {
        if (projectRepo.findByName(project.getName()) != null) {
            throw new ProjectNameAlreadyExistException("Project with the same name already exist :(");
        }
        else if (project.checkIfSomethingEmpty()) {
            throw new SomeFiledsAreNullException("Some fields are empty !");
        }
        return projectRepo.save(project);
    }

    public List<Project> getProjectsByLang(String lang) throws ProjectsNotFoundedException {
        List<Project> projects = projectRepo.findByLang(lang);
        if ( projects.size() == 0) {
            throw new ProjectsNotFoundedException("Project not founded");
        }
        return projects;
    }

    public Project getProjectsByName(String name) throws ProjectsNotFoundedException {
        Project project = projectRepo.findByName(name);
        if ( project == null) {
            throw new ProjectsNotFoundedException("Project not founded");
        }
        return project;
    }
}
