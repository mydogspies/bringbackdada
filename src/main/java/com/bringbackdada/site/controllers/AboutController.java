package com.bringbackdada.site.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller for the about page
 * @author mydogspies@zoho.com
 * @since 0.0.1
 */
@Controller
public class AboutController {

    private final Logger logger = LoggerFactory.getLogger(AboutController.class);

    @RequestMapping({"/site/about-bringbackdada","/site/about-bringbackdada.html"})
    public String getAboutPage(Model model){
        model.addAttribute("title_text", "Bringbackdada - ABOUT");

        logger.info("--> Calling about.html");
        return "about";
    }
}
