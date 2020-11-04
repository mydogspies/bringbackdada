package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.model.*;
import com.bringbackdada.site.services.GalleryItemService;
import com.bringbackdada.site.services.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class AdminChangeGalleryOrderController {

    private final Logger logger = LoggerFactory.getLogger(AdminChangeGalleryOrderController.class);

    private final GalleryService galleryService;
    private final GalleryItemService galleryItemService;

    public AdminChangeGalleryOrderController(GalleryService galleryService, GalleryItemService galleryItemService) {
        this.galleryService = galleryService;
        this.galleryItemService = galleryItemService;
    }

    @GetMapping(value={"/admin/choose-gallery-to-reorder", "admin/choose-gallery-to-reorder.html"})
    public String showChooseGalleryPage(Model model) {

        List<GalleryCommand> galleryList = galleryService.findAllAsCommands();

        model.addAttribute("galleries", galleryList);
        model.addAttribute("title_text", "Bringbackdada | admin | change gallery item order");

        logger.info("--> Called change_gallery_order.html");
        return "change_gallery_order";
    }

    @PostMapping(value={"/admin/reorder-gallery"})
    public String showReorderPage(Model model, @RequestParam("galleryId") Long id) {

        Gallery gallery = galleryService.findById(id);
        List<GalleryItem> items = new ArrayList<>(gallery.getGalleryItem());
        List<GalleryItemView> itemViews = new LinkedList<>();
        Long index = 0L;
        for (GalleryItem item : items) {
            GalleryItemView view = new GalleryItemView();
            view.setId(index);
            index++;
            view.setGalleryItemId(item.getId());
            Content content = item.getContent();
            view.setContentId(content.getId());
            view.setItemOrder(item.getItemOrder());
            view.setVisible(item.getVisible());
            itemViews.add(view);
        }

        GalleryItemStore formItems = new GalleryItemStore();
        formItems.setGalleryItems(itemViews);

        model.addAttribute("formItems", formItems);
        model.addAttribute("galleryItems", itemViews);
        model.addAttribute("title_text", "Bringbackdada | admin | set new order");

        return "change_gallery_reorder";
    }

    @PostMapping(value={"admin/change_gallery_reorder"})
    public String reorderGalleryItemsAndSave(@ModelAttribute GalleryItemStore formItems) {

        for (GalleryItemView view : formItems.getGalleryItems()) {
            System.out.println("gallery item id: " + view.getGalleryItemId());
            System.out.println("content id: " + view.getContentId());
            System.out.println("item order: " + view.getItemOrder());
            System.out.println("visible: " + view.getVisible());

            GalleryItem updatedItem = galleryItemService.findById(view.getGalleryItemId());
            updatedItem.setItemOrder(view.getItemOrder());
            updatedItem.setVisible(view.getVisible());
            galleryItemService.save(updatedItem);
        }

        return "admin-data-saved";
    }
}
