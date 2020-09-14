package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.BlogCommand;
import com.bringbackdada.site.model.ContentCategory;
import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.CreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;

@Controller
public class AdminAddBlogController {

    private final Logger logger = LoggerFactory.getLogger(AdminAddBlogController.class);

    private final ContentService contentService;
    private final BlogService blogService;
    private final CreatorService creatorService;

    public AdminAddBlogController(ContentService service, BlogService blogService, CreatorService creatorService) {
        this.contentService = service;
        this.blogService = blogService;
        this.creatorService = creatorService;
    }

    @GetMapping(value={"/admin/add-new-blog", "admin/add-new-content.html"})
    public String showAddBlogPage(Model model) {

        model.addAttribute("contentSet", contentService.findAll());
        model.addAttribute("creatorSet", creatorService.findAll());
        model.addAttribute("title_text", "Bringbackdada | admin | add new blog");

        logger.info("--> Called add-blog.html");
        return "add-blog";
    }

    @Transactional
    @PostMapping(value={"admin/save-new-blog"})
    public String saveOrUpdateBlog(@RequestParam("entryName") String entryName,
                                   @RequestParam("entryContent") String entryContent,
                                   @RequestParam("entrySnippet") String entrySnippet,
                                   @RequestParam("category") List<ContentCategory> category,
                                   @RequestParam("content") Long imageId,
                                   @RequestParam("creator") Long creatorId) {

        BlogCommand command = new BlogCommand();
        command.setEntryName(entryName);
        command.setEntryContent(entryContent);
        command.setContentSnippet(entrySnippet);
        command.setCategory(new HashSet<>(category));
        command.setContentId(imageId);

        Instant instant = Instant.now();
        command.setMilliseconds(instant);

        Creator blogCreator = creatorService.findById(creatorId);
        command.setCreator(blogCreator);
        blogService.saveBlogCommand(command);

        return "admin-data-saved";
    }
}
