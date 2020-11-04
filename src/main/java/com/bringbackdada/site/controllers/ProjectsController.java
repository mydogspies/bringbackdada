package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.commands.converters.GalleryToGalleryCmd;
import com.bringbackdada.site.commands.converters.ProjectToProjectCommand;
import com.bringbackdada.site.exceptions.NotFoundException;
import com.bringbackdada.site.model.*;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryItemService;
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
 * Note: This controller requires the internal objects ProjectView and ProjectViewItem.
 * @since 0.0.1
 * @see ProjectView
 * @see ProjectViewItem
 */
@Controller
public class ProjectsController {

    private final Logger logger = LoggerFactory.getLogger(ProjectsController.class);

    private final ProjectService projectService;
    private final ContentService contentService;
    private final GalleryItemService galleryItemService;
    private final ProjectToProjectCommand projectToProjectCommand;
    private final GalleryToGalleryCmd galleryToGalleryCmd;
    private final ContentToContentCmd contentToContentCmd;

    public ProjectsController(ProjectService projectService, ContentService contentService, GalleryItemService galleryItemService, ProjectToProjectCommand projectToProjectCommand, GalleryToGalleryCmd galleryToGalleryCmd, ContentToContentCmd contentToContentCmd) {
        this.projectService = projectService;
        this.contentService = contentService;
        this.galleryItemService = galleryItemService;
        this.projectToProjectCommand = projectToProjectCommand;
        this.galleryToGalleryCmd = galleryToGalleryCmd;
        this.contentToContentCmd = contentToContentCmd;
    }

    @RequestMapping({"/site/photo-projects", "/site/photo-projects.html"})
    public String getProjectsPage(Model model) {

        ProjectView projectView = new ProjectView();
        List<ProjectViewItem> projectViewItemList = new ArrayList<>();
        List<Project> projects = projectService.sortProjectByProjectOrder(projectService.findAll());
        Integer id = 0;

        for (Project project : projects) {
            ProjectViewItem projectViewItem = new ProjectViewItem();
            List<ContentCommand> contentList = new ArrayList<>();

            // if the project is set to be visible on the project page...
            if (project.getRollVisible()) {
                List<Gallery> galleryList = project.getGallery();
                for (Gallery gallery : galleryList) {
                    // if the gallery is set to be globally visible
                    if (gallery.getVisible()) {
                        id++;
                        for (GalleryItem item : galleryItemService
                                .sortGalleryItemByGalleryItemOrder(gallery.getGalleryItem())) {
                            ContentCommand contentCmd = contentToContentCmd.convert(item.getContent());
                            // if the content is set to be globally visible
                            if (contentCmd.getVisible()) {
                                contentList.add(contentCmd);
                            }
                        }
                    }
                }
                projectViewItem.setItemId(id);
                projectViewItem.setFeaturedContent(contentList);
                projectViewItem.setProjectCmd(projectToProjectCommand.convert(project));
            }
            projectViewItemList.add(projectViewItem);
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
