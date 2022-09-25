package org.example.SpringAllProjectSite.controller;

import org.example.SpringAllProjectSite.database.Project;
import org.example.SpringAllProjectSite.repos.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/")
    public String zero(Map<String, Object> model) {
        return "zero";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Project> projects = projectRepo.findAll();
        model.put("projects", projects);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @RequestParam String name,
            @RequestParam String title,
            @RequestParam String text,
            @RequestParam String lang,
            @RequestParam String link,
            Map<String, Object> model) {
        Project project = new Project(name, title, text, lang, link);

        Iterable<Project> projects = projectRepo.findAll();
        model.put("projects", projects);

        projectRepo.save(project);
        return "redirect:/main";
    }
}