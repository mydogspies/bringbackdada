package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.commands.converters.CreatorToCreatorCmd;
import com.bringbackdada.site.model.ContentCategory;
import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.model.License;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.CreatorService;
import com.bringbackdada.site.services.LicenseService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminAddContentController {

    private final Logger logger = LoggerFactory.getLogger(AdminAddContentController.class);

    private final CreatorService creatorService;
    private final LicenseService licenseService;
    private final ContentService contentService;

    public AdminAddContentController(CreatorService creatorService, LicenseService licenseService, ContentService contentService) {
        this.creatorService = creatorService;
        this.licenseService = licenseService;
        this.contentService = contentService;
    }

    @GetMapping(value={"/admin/add-new-content", "/admin/add-new-content.html"})
    public String getAddContentPage(Model model) {

        model.addAttribute("creatorSet", creatorService.findAll());
        model.addAttribute("licenseSet", licenseService.findAll());

        model.addAttribute("title_text", "Bringbackdada | admin | add new content");

        logger.info("--> Called add-content.html");
        return "add-content";
    }

    @PostMapping(value={"/admin/save-new-content"})
    public String saveOrUpdateContent(@RequestParam("contentTitle") String contentTitle,
                                      @RequestParam("imageFile") MultipartFile file,
                                      @RequestParam("description") String description,
                                      @RequestParam("license") Long licenseId,
                                      @RequestParam("creators") Long creatorId,
                                      @RequestParam("category")ContentCategory category){

        ContentCommand command = new ContentCommand();

        Byte[] byteFile = null;
        try {
            byteFile = convertIntoByteArray(file);
        } catch (IOException e) {
            // TODO handle exception properly
            logger.error("Multipart file could not be converted:", e);
        }

        command.setImageFile(byteFile);
        command.setContentTitle(contentTitle);
        command.setDescription(description);
        command.setCategory(category);

        License license = licenseService.findById(licenseId);
        command.setLicense(license);

        // TODO implement creators

        ContentCommand savedCommand = contentService.saveContentCommand(command);


        return "add-content";
    }

    private Byte[] convertIntoByteArray (MultipartFile file) throws IOException {

        Byte[] byteArray = new Byte[file.getBytes().length];
        int i = 0;
        for (byte b : file.getBytes()) {
            byteArray[i++] = b;
        }

        return byteArray;
    }
}
