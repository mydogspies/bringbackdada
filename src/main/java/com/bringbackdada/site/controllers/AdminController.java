package com.bringbackdada.site.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping(value={"/admin", "admin-html"})
    public String showAdminPage(Model model) {

        model.addAttribute("title_text", "Bringbackdada | admin");

        logger.info("--> Called admin.html");
        return "admin";
    }
}
