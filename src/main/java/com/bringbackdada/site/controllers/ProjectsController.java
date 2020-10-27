package com.bringbackdada.site.controllers;

import com.bringbackdada.site.exceptions.NotFoundException;
import com.bringbackdada.site.model.*;
import com.bringbackdada.site.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The controller for the projects page
 * @since 0.0.1
 */
@Controller
public class ProjectsController {

    private final Logger logger = LoggerFactory.getLogger(ProjectsController.class);

    private final ProjectService projectService;

    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping({"/site/photo-projects","/site/photo-projects.html"})
    public String getProjectsPage(Model model){

        ProjectView projectView = new ProjectView();
        List<ProjectViewItem> projectViewItemList = new ArrayList<>();
        List<Project> projects = projectService.findAll();

        for (Project project : projects) {

            ProjectViewItem projectItem = new ProjectViewItem();
            List<Content> contentList = new ArrayList<>();
            List<Gallery> galleryList = project.getGallery();

            for (Gallery gallery : galleryList) {
                contentList.addAll(gallery.getContent());
            }
            projectItem.setProject(project);
            projectItem.setContent(contentList);
            projectViewItemList.add(projectItem);
        }
        projectView.setProjectItem(projectViewItemList);

        if (!projects.isEmpty()) {

            model.addAttribute("title_text", "Bringbackdada | Fine art photography projects");
            model.addAttribute("projectViewList", projectView);

            logger.info("--> Calling projects.html");
            return "projects";
        } else {
            throw new NotFoundException("getProjectsPage(): No projects returned from database");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView dataNotFound404(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");

        model.addAttribute("title_text", "Bringbackdada | No projects found");

        return modelAndView;
    }
}
