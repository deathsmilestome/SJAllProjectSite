package org.example.SpringAllProjectSite.service;

import org.example.SpringAllProjectSite.database.Project;
import org.example.SpringAllProjectSite.exception.ProjectNameAlreadyExistException;
import org.example.SpringAllProjectSite.exception.SomeFiledsAreNullException;
import org.example.SpringAllProjectSite.repos.ProjectRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepo projectRepo;

    @MockBean
    private Project project;

    @Test
    @DisplayName("Should create the project")
    void createProjectWithValidName() throws SomeFiledsAreNullException, ProjectNameAlreadyExistException {
        when(projectRepo.findByName(project.getName())).thenReturn(null);
        when(project.checkIfSomethingEmpty()).thenReturn(false);

        projectService.createProject(project);

        verify(projectRepo).save(project);
    }

    @Test
    @DisplayName("Shouldn't create the project, return Project with the same name already exist :(")
    void createProjectWithInvalidName() {
        when(projectRepo.findByName(project.getName())).thenReturn(project);

        ProjectNameAlreadyExistException exception = assertThrows(
                ProjectNameAlreadyExistException.class, () -> {projectService.createProject(project);});

        assertEquals("Project with the same name already exist :(", exception.getMessage());
    }

    @Test
    @DisplayName("Shouldn't create the project, return Some fields are empty !")
    void createProjectWithEmptyFields() {
        when(projectRepo.findByName(project.getName())).thenReturn(null);
        when(project.checkIfSomethingEmpty()).thenReturn(true);

        SomeFiledsAreNullException exception = assertThrows(
                SomeFiledsAreNullException.class, () -> {projectService.createProject(project);});

        assertEquals("Some fields are empty !", exception.getMessage());
    }
}