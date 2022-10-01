package org.example.SpringAllProjectSite.API;

import org.example.SpringAllProjectSite.database.Project;
import org.example.SpringAllProjectSite.exception.ProjectNameAlreadyExistException;
import org.example.SpringAllProjectSite.exception.SomeFiledsAreNullException;
import org.example.SpringAllProjectSite.repos.ProjectRepo;
import org.example.SpringAllProjectSite.service.ProjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectControllerTest {

    @Autowired
    private ProjectController projectController;

    @MockBean
    private Project project;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private ProjectRepo projectRepo;

    @Test
    @DisplayName("Should create the project and return Project saved")
    void createProjectWithValidName() throws ProjectNameAlreadyExistException, SomeFiledsAreNullException {
        when(projectRepo.findByName(project.getName())).thenReturn(null);
        when(project.checkIfSomethingEmpty()).thenReturn(false);

        assertDoesNotThrow(()-> projectController.createProject(project));
        assertEquals(ResponseEntity.ok("Project saved )"),projectController.createProject(project));
    }

    @Test
    @DisplayName("Should throw an error and return Project with the same name already exist :(")
    void createProjectWithInvalidName() throws ProjectNameAlreadyExistException, SomeFiledsAreNullException {
        when(projectService.createProject(project)).thenThrow(new ProjectNameAlreadyExistException("Project with the same name already exist :("));

        assertEquals(ResponseEntity.badRequest().body("Project with the same name already exist :("),projectController.createProject(project));
    }

    @Test
    @DisplayName("Should throw an error and return Project with the same name already exist :(")
    void createProjectWithEmptyFields() throws ProjectNameAlreadyExistException, SomeFiledsAreNullException {
        when(projectService.createProject(project)).thenThrow(new SomeFiledsAreNullException("Some fields are empty !"));

        assertEquals(ResponseEntity.badRequest().body("Some fields are empty !"),projectController.createProject(project));

    }
}