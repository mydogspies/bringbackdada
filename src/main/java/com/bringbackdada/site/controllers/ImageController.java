package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.services.CreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class ImageController {

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    private final CreatorService creatorService;

    public ImageController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @RequestMapping(value={"admin/add-new-content", "admin/add-new-content.html"}, method= RequestMethod.GET)
    public String getAddContentPage(Model model) {

        Set<Creator> creatorSet = creatorService.findAll();
        model.addAttribute("creatorSet", creatorSet);

        model.addAttribute("creator", new Creator());
        model.addAttribute("title_text", "Bringbackdada | admin | add new content");

        logger.info("--> Called add-content.html");
        return "add-content";
    }

}
