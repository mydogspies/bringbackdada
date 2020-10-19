package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.commands.converters.CreatorToCreatorCmd;
import com.bringbackdada.site.model.ContentCategory;
import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.model.License;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.CreatorService;
import com.bringbackdada.site.services.ImageService;
import com.bringbackdada.site.services.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminAddContentController {

    private final Logger logger = LoggerFactory.getLogger(AdminAddContentController.class);

    private final CreatorService creatorService;
    private final LicenseService licenseService;
    private final ContentService contentService;
    private final ImageService imageService;

    public AdminAddContentController(CreatorService creatorService, LicenseService licenseService, ContentService contentService, ImageService imageService) {
        this.creatorService = creatorService;
        this.licenseService = licenseService;
        this.contentService = contentService;
        this.imageService = imageService;
    }

    @GetMapping(value={"/admin/add-new-content", "/admin/add-new-content.html"})
    public String showAddContentPage(Model model) {

        model.addAttribute("creatorSet", creatorService.findAll());
        model.addAttribute("licenseSet", licenseService.findAll());
        model.addAttribute("title_text", "Bringbackdada | admin | add new content");

        logger.info("--> Called add-content.html");
        return "add-content";
    }

    @Transactional
    @PostMapping(value={"/admin/save-new-content"})
    public String saveOrUpdateContent(@RequestParam("contentTitle") String contentTitle,
                                      @RequestParam("imageFile") MultipartFile file,
                                      @RequestParam("description") String description,
                                      @RequestParam("license") Long licenseId,
                                      @RequestParam("creators") List<Long> creatorIds,
                                      @RequestParam("category") ContentCategory category,
                                      @RequestParam("featured") Integer featured,
                                      @RequestParam("contentOrder") Integer order,
                                      @RequestParam("visible") Boolean visible){

        ContentCommand command = new ContentCommand();

        byte[] byteFile = imageService.convertToByteArray(file);

        command.setImageFile(byteFile);
        command.setContentTitle(contentTitle);
        command.setDescription(description);
        command.setCategory(category);
        command.setOnFrontPage(featured != 0);
        command.setContentOrder(order);
        command.setVisible(visible);

        License license = licenseService.findById(licenseId);
        command.setLicense(license);

        Set<CreatorCommand> creatorSet = new HashSet<>();
        for (Long id : creatorIds) {
            Creator creator = creatorService.findById(id);
            CreatorCommand creatorCmd = new CreatorToCreatorCmd().convert(creator);
            creatorSet.add(creatorCmd);
        }
        command.setCreators(creatorSet);

        // TODO implement tags
        // TODO implement models

        contentService.saveContentCommand(command);

        return "admin-data-saved";
    }
}
