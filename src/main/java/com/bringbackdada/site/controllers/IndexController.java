package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Tags;
import com.bringbackdada.site.repositories.TagsRepository;
import com.bringbackdada.site.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * The controller for the main index page
 * @since 0.0.1
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private final ProjectService projectService;

    public IndexController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndexPage(Model model){

        model.addAttribute("title_text", "Bringbackdada - INDEX");
        model.addAttribute("projects", projectService.findAll());

        logger.info("--> Called index.html");
        return "index";
    }
}
