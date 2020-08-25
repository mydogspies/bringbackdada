package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.services.CreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final CreatorService creatorService;

    public AdminController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    // CREATOR ENTRY

    @RequestMapping(value={"admin/add-entries","admin/add-entries.html"}, method=RequestMethod.GET)
    public String getEntryInputPage(Model model) {

        Set<Creator> creatorSet = creatorService.findAll();
        model.addAttribute("creatorSet", creatorSet);

        model.addAttribute("creator", new Creator());
        model.addAttribute("title_text", "Bringbackdada | admin | add entries");

        logger.info("--> Called add-project.html");
        return "add_db_entries";
    }

    @RequestMapping(value={"/save_creator"}, method=RequestMethod.POST)
    public String onCreatorSubmit(@ModelAttribute Creator creator, Model model) {

        creatorService.save(creator);

        logger.info("--> saved CREATOR object and called add-project.html");
        return "add_db_entries";
    }
}
