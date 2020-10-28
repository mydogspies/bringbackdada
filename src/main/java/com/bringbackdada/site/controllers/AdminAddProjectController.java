package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.*;
import com.bringbackdada.site.commands.converters.BlogToBlogCmd;
import com.bringbackdada.site.commands.converters.CreatorToCreatorCmd;
import com.bringbackdada.site.commands.converters.GalleryToGalleryCmd;
import com.bringbackdada.site.commands.converters.TagToTagCmd;
import com.bringbackdada.site.model.ProjectCategory;
import com.bringbackdada.site.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    private final ProjectService projectService;
    private final TagService tagService;
    private final GalleryToGalleryCmd galleryToGalleryCmd;
    private final CreatorToCreatorCmd creatorToCreatorCmd;
    private final BlogToBlogCmd blogToBlogCmd;
    private final TagToTagCmd tagToTagCmd;

    public AdminAddProjectController(GalleryService galleryService, CreatorService creatorService, BlogService blogService, ProjectService projectService, TagService tagService, GalleryToGalleryCmd galleryToGalleryCmd, CreatorToCreatorCmd creatorToCreatorCmd, BlogToBlogCmd blogToBlogCmd, TagToTagCmd tagToTagCmd) {
        this.galleryService = galleryService;
        this.creatorService = creatorService;
        this.blogService = blogService;
        this.projectService = projectService;
        this.tagService = tagService;
        this.galleryToGalleryCmd = galleryToGalleryCmd;
        this.creatorToCreatorCmd = creatorToCreatorCmd;
        this.blogToBlogCmd = blogToBlogCmd;
        this.tagToTagCmd = tagToTagCmd;
    }

    @GetMapping(value={"/admin/add-new-project", "admin/add-new-project.html"})
    public String showAddProjectPage(Model model) {

        model.addAttribute("gallerySet", galleryService.findAllAsCommands());
        model.addAttribute("creatorSet", creatorService.findAllAsCommands());
        model.addAttribute("blogSet", blogService.findAllAsCommands());
        model.addAttribute("title_text", "Bringbackdada | admin | add new project");

        logger.info("--> Called add-project.html");
        return "add-project";
    }

    @PostMapping(value={"/admin/save-new-project"})
    public String saveOrUpdateProject(@RequestParam("projectTitle") String projectTitle,
                                      @RequestParam("category") ProjectCategory category,
                                      @RequestParam("description") String description,
                                      @RequestParam("galleries") List<Long> galleryIdList,
                                      @RequestParam("creators") List<Long> creatorIdList,
                                      @RequestParam("blogs") List<Long> blogIdList,
                                      @RequestParam("tags") String tags,
                                      @RequestParam("rollVisible") Boolean vis,
                                      @RequestParam("projectOrder") Integer order){

        ProjectCommand command = new ProjectCommand();
        command.setName(projectTitle);
        command.setProjectCategory(category);
        command.setDescription(description);
        command.setGallery(getGalleryList(galleryIdList));
        command.setCreator(getCreatorList(creatorIdList));
        // TODO implement the tag function -> SEE BELOW!
        command.setTags(makeTagStringToList(tags));
        command.setBlog(getBlogList(blogIdList));
        command.setRollVisible(vis);
        command.setProjectOrder(order);

        projectService.saveProjectCommand(command);
        return "admin-data-saved";
    }

    /**
     * Takes the a of id's and returns a list of gallery command objects.
     * @param galleryIdList
     * @return list of GalleryCommand objects.
     */
    private List<GalleryCommand> getGalleryList(List<Long> galleryIdList) {
        List<GalleryCommand> galleryList = new ArrayList<>();
        for (Long id : galleryIdList) {
            galleryList.add(galleryToGalleryCmd.convert(galleryService.findById(id)));
        }
        return galleryList;
    }

    /**
     * Takes a list of id's and returns a list of creator command objects.
     * @param creatorIdList
     * @return list of CreatorCommand objects
     */
    private List<CreatorCommand> getCreatorList(List<Long> creatorIdList) {
        List<CreatorCommand> creatorList = new ArrayList<>();
        for (Long id : creatorIdList) {
            creatorList.add(creatorToCreatorCmd.convert(creatorService.findById(id)));
        }
        return creatorList;
    }

    /**
     * Takes a list of id's and returns a list of blog command objects
     * @param blogIdList
     * @return list of BlogCommand objects
     */
    private List<BlogCommand> getBlogList(List<Long> blogIdList) {
        List<BlogCommand> blogList = new ArrayList<>();
            for (Long id : blogIdList) {
                blogList.add(blogToBlogCmd.convert(blogService.findById(id)));
            }
        return blogList;
    }

    /**
     * Takes a comma-separated string of tag names, parses it and checks/updates against database.
     * @param tags comma separated string of tag names
     * @return list of TagCommand objects
     */
    private List<TagCommand> makeTagStringToList(String tags) {
        List<TagCommand> tagList = new ArrayList<>();
        if (tags.equals("")) {
            tagList.add(tagToTagCmd.convert(tagService.findById(15L)));
        } else {
            // TODO implement the tag parsing method!
            // The actual db logic should be in TagServices!!
        }
        return tagList;
    }
}
