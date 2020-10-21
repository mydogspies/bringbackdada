package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.CreatorService;
import com.bringbackdada.site.services.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The controller for adding new projects
 * @since 0.1.0
 */
@Controller
public class AdminAddProjectController {

    private final Logger logger = LoggerFactory.getLogger(AdminAddProjectController.class);

    private final GalleryService galleryService;
    private final CreatorService creatorService;
    private final BlogService blogService;

    public AdminAddProjectController(GalleryService galleryService, CreatorService creatorService, BlogService blogService) {
        this.galleryService = galleryService;
        this.creatorService = creatorService;
        this.blogService = blogService;
    }

    @GetMapping(value={"/admin/add-new-project", "admin/add-new-project.html"})
    public String showAddProjectPage(Model model) {

        model.addAttribute("gallerySet", galleryService.findAll());
        model.addAttribute("creatorSet", creatorService.findAll());
        model.addAttribute("blogSet", blogService.findAll());
        model.addAttribute("title_text", "Bringbackdada | admin | add new project");

        logger.info("--> Called add-project.html");
        return "add-project";
    }

    @PostMapping(value={"/admin/save-new-project"})
    public String saveOrUpdateProject() {

        return "admin-data-saved";
    }
}
