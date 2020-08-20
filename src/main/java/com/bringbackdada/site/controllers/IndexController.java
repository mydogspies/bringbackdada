package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Tags;
import com.bringbackdada.site.repositories.TagsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * The controller for the main index page
 * @since 0.0.1
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private TagsRepository tagsRepository;

    public IndexController(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndexPage(Model model){

        Optional<Tags> tags = tagsRepository.findById(1L);
        model.addAttribute("tag", tags.get().getTag());

        model.addAttribute("title_text", "Bringbackdada - INDEX");

        logger.info("--> Called index.html");
        return "index";
    }
}
