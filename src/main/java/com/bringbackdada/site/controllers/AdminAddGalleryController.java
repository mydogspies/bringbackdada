package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminAddGalleryController {

    private final Logger logger = LoggerFactory.getLogger(AdminAddGalleryController.class);

    private final ContentService contentService;
    private final GalleryService galleryService;
    private final ContentToContentCmd contentConverter;

    public AdminAddGalleryController(ContentService contentService, GalleryService galleryService, ContentToContentCmd contentConverter) {
        this.contentService = contentService;
        this.galleryService = galleryService;
        this.contentConverter = contentConverter;
    }

    @GetMapping(value={"/admin/add-new-gallery", "/admin/add-new-gallery.html"})
    public String showAddGalleryPage(Model model) {

        model.addAttribute("contentSet", contentService.findAll());
        model.addAttribute("title_text", "Bringbackdada | admin | add a new gallery");

        logger.info("--> Called add-gallery.html");
        return "add-gallery";
    }

    @Transactional
    @PostMapping(value={"/admin/save-new-gallery"})
    public String saveOrUpdateGallery(@RequestParam("content") List<Long> contentList,
                                      @RequestParam("description") String description,
                                      @RequestParam("featured") Integer featured) {

        GalleryCommand command = new GalleryCommand();
        command.setDescription(description);
        command.setIsFeatured(featured != 0);

        List<ContentCommand> contentCmdList = new ArrayList<>();
        for (Long id : contentList) {
            ContentCommand contentCmd = contentConverter.convert(contentService.findById(id));
            contentCmdList.add(contentCmd);
        }
        command.setContent(contentCmdList);
        galleryService.saveGalleryCommand(command);

        return "admin-data-saved";
    }
}
