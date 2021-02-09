package com.bringbackdada.site.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoScriptController {

    private final Logger logger = LoggerFactory.getLogger(NoScriptController.class);

    @GetMapping(value = "/site/noscript")
    public String getGeneralNoscriptPage(Model model) {

        model.addAttribute("title_text", "Bringbackdada.com - Javascript is disabled on this browser!");
        return "noscript-default";
    }
}
