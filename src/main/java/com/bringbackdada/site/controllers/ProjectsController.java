package com.bringbackdada.site.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller for the projects page
 * @since 0.0.1
 */
@Controller
public class ProjectsController {

    private final Logger logger = LoggerFactory.getLogger(ProjectsController.class);

    @RequestMapping({"photo-projects","photo-projects.html"})
    public String getAboutPage(Model model){
        model.addAttribute("title_text", "Bringbackdada - PROJECTS");

        logger.info("--> Calling projects.html");
        return "projects";
    }
}
