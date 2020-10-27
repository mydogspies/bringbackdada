package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminDeleteProjectController {

    private final Logger logger = LoggerFactory.getLogger(AdminDeleteProjectController.class);

    private final ProjectService projectService;

    public AdminDeleteProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value={"/admin/delete-project", "admin/delete-project.html"})
    public String showAddProjectPage(Model model) {

        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("title_text", "Bringbackdada | admin | delete a project");

        logger.info("--> Called delete-project.html");
        return "delete-project";
    }

    @Transactional
    @PostMapping(value={"/admin/delete-a-project"})
    public String saveOrUpdateGallery(@RequestParam("projectToDelete") Long id) {

        projectService.deleteById(id);

        // TODO return proper page!
        return "admin-data-saved";
    }
}
