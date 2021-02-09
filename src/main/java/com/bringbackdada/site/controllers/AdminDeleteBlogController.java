package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminDeleteBlogController {

    private final Logger logger = LoggerFactory.getLogger(AdminDeleteBlogController.class);

    private final BlogService blogService;

    public AdminDeleteBlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(value={"/admin/delete-blog", "admin/delete-blog.html"})
    public String showDeleteCreatorPage(Model model) {

        model.addAttribute("allBlogs", blogService.findAll());
        model.addAttribute("title_text", "Bringbackdada | admin | delete a blog");

        logger.info("--> Called delete-blog.html");
        return "delete-blog";
    }

    @Transactional
    @PostMapping(value={"/admin/delete-this-blog"})
    public String deleteCreator(@RequestParam("blogToDelete") Long id) {

        System.out.println("id = " + id);

        blogService.deleteById(id);

        // TODO return proper page!
        return "admin-data-saved";
    }

}
