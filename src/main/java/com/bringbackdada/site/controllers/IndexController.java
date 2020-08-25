package com.bringbackdada.site.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller for the main index page
 * @since 0.0.1
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndexPage(Model model){

        model.addAttribute("title_text", "Bringbackdada.com - INDEX");

        logger.info("--> Called index.html");
        return "index";
    }
}
