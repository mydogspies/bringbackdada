package com.bringbackdada.site.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller for the blog page
 * @author mydogspies@zoho.com
 * @since 0.0.1
 */
@Controller
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @RequestMapping({"blog","blog.html"})
    public String getBlog(){
        logger.info("--> Calling blog.html");
        return "blog";
    }
}
