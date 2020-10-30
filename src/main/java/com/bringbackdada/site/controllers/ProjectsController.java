package com.bringbackdada.site.controllers;

import com.bringbackdada.site.exceptions.NotFoundException;
import com.bringbackdada.site.model.*;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.ProjectService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the projects page
 *
 * @since 0.0.1
 */
@Controller
public class ProjectsController {

    private final Logger logger = LoggerFactory.getLogger(ProjectsController.class);

    private final ProjectService projectService;
    private final ContentService contentService;

    public ProjectsController(ProjectService projectService, ContentService contentService) {
        this.projectService = projectService;
        this.contentService = contentService;
    }

    @RequestMapping({"/site/photo-projects", "/site/photo-projects.html"})
    public String getProjectsPage(Model model) {

        ProjectView projectView = new ProjectView();
        List<ProjectViewItem> projectViewItemList = new ArrayList<>();
        List<Project> projects = projectService.sortProjectByProjectOrder(projectService.findAll());
        Integer id = 0;

        for (Project project : projects) {

            ProjectViewItem projectItem = new ProjectViewItem();
            List<Content> contentList = new ArrayList<>();
            List<Gallery> galleryList;

            // Only add project if it's flagged as visible for the project page
            if (project.getRollVisible()) {
                galleryList = project.getGallery();
                id++;

                // TODO implement new GalleryItem code!!
//                for (GalleryOld gallery : galleryList) {
//                    contentList.addAll(gallery.getContent());
//                }

                projectItem.setProject(project);
                projectItem.setContent(contentService.sortContentByContentOrder(contentList));
                projectItem.setItemId(id);
                projectViewItemList.add(projectItem);
            }
        }
        projectView.setProjectItem(projectViewItemList);

        if (!projects.isEmpty()) {

            model.addAttribute("galleries", id);
            model.addAttribute("title_text", "Bringbackdada | Fine art photo series and projects in Berlin");
            model.addAttribute("projectViewList", projectView);

            logger.info("--> Calling projects.html");
            return "projects";
        } else {
            throw new NotFoundException("getProjectsPage(): No projects returned from database");
        }
    }

    @GetMapping("/project/image/{id}")
    public void showGalleryImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        response.setContentType("image/jpeg");

        Content content = contentService.findById(id);

        if (content != null) {
            InputStream is = new ByteArrayInputStream(content.getImageFile());
            IOUtils.copy(is, response.getOutputStream());
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
