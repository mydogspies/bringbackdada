package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.CreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminDeleteCreatorController {

    private final Logger logger = LoggerFactory.getLogger(AdminDeleteCreatorController.class);

    private final CreatorService creatorService;

    public AdminDeleteCreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @GetMapping(value={"/admin/delete-creator", "admin/delete-creator.html"})
    public String showDeleteCreatorPage(Model model) {

        model.addAttribute("allCreators", creatorService.findAll());
        model.addAttribute("title_text", "Bringbackdada | admin | delete creator");

        logger.info("--> Called delete-creator.html");
        return "delete-creator";
    }

    @Transactional
    @PostMapping(value={"/admin/delete-this-creator"})
    public String deleteCreator(@RequestParam("creatorToDelete") Long id) {

        System.out.println("id = " + id);

        creatorService.deleteById(id);

        // TODO return proper page!
        return "admin-data-saved";
    }
}
