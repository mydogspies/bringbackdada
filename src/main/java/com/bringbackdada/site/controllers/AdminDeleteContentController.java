package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminDeleteContentController {

    private final Logger logger = LoggerFactory.getLogger(AdminDeleteContentController.class);

    private final ContentService contentService;

    public AdminDeleteContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping(value={"/admin/delete-content", "admin/delete-content.html"})
    public String showAddProjectPage(Model model) {

        model.addAttribute("allContent", contentService.findAll());
        model.addAttribute("title_text", "Bringbackdada | admin | delete content");

        logger.info("--> Called delete-content.html");
        return "delete-content";
    }

    @Transactional
    @PostMapping(value={"/admin/delete-this-content"})
    public String saveOrUpdateGallery(@RequestParam("contentToDelete") Long id) {

        System.out.println("id = " + id);

        contentService.deleteById(id);

        // TODO return proper page!
        return "admin-data-saved";
    }
}
