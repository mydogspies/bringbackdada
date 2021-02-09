package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.services.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminDeleteGalleryController {

    private final Logger logger = LoggerFactory.getLogger(AdminDeleteGalleryController.class);

    private final GalleryService galleryService;

    public AdminDeleteGalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping(value={"/admin/delete-gallery", "admin/delete-gallery.html"})
    public String showAddProjectPage(Model model) {

        model.addAttribute("galleries", galleryService.findAllAsCommands());
        model.addAttribute("title_text", "Bringbackdada | admin | delete a gallery");

        logger.info("--> Called delete-gallery.html");
        return "delete-gallery";
    }

    @Transactional
    @PostMapping(value={"/admin/delete-a-gallery"})
    public String saveOrUpdateGallery(@RequestParam("galleryToDelete") Long id) {

        galleryService.deleteById(id);


        // TODO return proper page!
        return "admin-data-saved";
    }
}
