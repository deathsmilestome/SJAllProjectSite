package org.example.SpringAllProjectSite.API;

import org.example.SpringAllProjectSite.database.Project;
import org.example.SpringAllProjectSite.exception.ProjectNameAlreadyExistException;
import org.example.SpringAllProjectSite.exception.ProjectsNotFoundedException;
import org.example.SpringAllProjectSite.exception.SomeFiledsAreNullException;
import org.example.SpringAllProjectSite.repos.ProjectRepo;
import org.example.SpringAllProjectSite.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity createProject(@RequestBody Project project) {
        try {
            projectService.createProject(project);
            return ResponseEntity.ok("Project saved )");
        } catch (ProjectNameAlreadyExistException | SomeFiledsAreNullException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Save failed");
        }
    }

    @GetMapping()
    public ResponseEntity getProjectsByLang(@RequestParam String lang) {
        try {
            return ResponseEntity.ok(projectService.getProjectsByLang(lang));
        } catch (ProjectsNotFoundedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Save failed");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity getProjectsByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(projectService.getProjectsByName(name));
        } catch (ProjectsNotFoundedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Save failed");
        }
    }
}
