package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.commands.GalleryItemCommand;
import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.commands.converters.GalleryItemToGalleryItemCmd;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.model.GalleryItem;
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
    private final GalleryItemToGalleryItemCmd galleryConverter;

    public AdminAddGalleryController(ContentService contentService, GalleryService galleryService, ContentToContentCmd contentConverter, GalleryItemToGalleryItemCmd galleryConverter) {
        this.contentService = contentService;
        this.galleryService = galleryService;
        this.contentConverter = contentConverter;
        this.galleryConverter = galleryConverter;
    }

    @GetMapping(value={"/admin/add-new-gallery", "/admin/add-new-gallery.html"})
    public String showAddGalleryPage(Model model) {

        model.addAttribute("contentSet", contentService.findAllAsCommands());
        model.addAttribute("title_text", "Bringbackdada | admin | add a new gallery");

        logger.info("--> Called add-gallery.html");
        return "add-gallery";
    }

    @Transactional
    @PostMapping(value={"/admin/save-new-gallery"})
    public String saveOrUpdateGallery(@RequestParam("content") List<Long> contentList,
                                      @RequestParam("description") String description,
                                      @RequestParam("featured") Integer featured,
                                      @RequestParam("title") String title,
                                      @RequestParam("galleryOrder") Integer order,
                                      @RequestParam("visible") Boolean visible) {

        GalleryCommand command = new GalleryCommand();
        command.setDescription(description);
        command.setFrontPageFeatured(featured != 0);
        command.setGalleryName(title);
        command.setGalleryOrder(order);
        command.setVisible(visible);

        List<GalleryItemCommand> galleryItemCmdList = new ArrayList<>();
        for (Long id : contentList) {
            GalleryItem galleryItem = new GalleryItem();
            Content content = contentService.findById(id);
            galleryItem.setContent(content);
            galleryItem.setItemOrder(content.getContentOrder());
            galleryItem.setVisible(true);
            galleryItemCmdList.add(galleryConverter.convert(galleryItem));
        }
        command.setGalleryItem(galleryItemCmdList);
        galleryService.saveGalleryCommand(command);

        return "admin-data-saved";
    }
}