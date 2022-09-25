package org.example.SpringAllProjectSite.controller;

import org.example.SpringAllProjectSite.database.Project;
import org.example.SpringAllProjectSite.repos.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class FilterController {
    @Autowired
    private ProjectRepo projectRepo;

    @PostMapping("/filter")
    public String filter(
            @RequestParam String filter,
            Map<String, Object> model) {
        Iterable<Project> projects;

        if (filter != null && !filter.isEmpty()) {
            projects = projectRepo.findByLang(filter);
        } else {
            projects = projectRepo.findAll();
        }

        model.put("projects", projects);
        return "redirect:/main";
    }
}
