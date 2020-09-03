package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The controller for the main index page
 * @since 0.0.1
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private final GalleryService galleryService;

    public IndexController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndexPage(Model model){

        Gallery featuredGallery = galleryService.getGalleryByFeatured();
        List<Content> contentList = null;

        if (featuredGallery != null) {
            contentList = new ArrayList<>(featuredGallery.getContent());
        }

        logger.debug("contentList = " + contentList);

        model.addAttribute("contentList", contentList);
        model.addAttribute("title_text", "Bringbackdada.com - HOME");

        logger.info("--> Called home.html");
        return "home";
    }
}
