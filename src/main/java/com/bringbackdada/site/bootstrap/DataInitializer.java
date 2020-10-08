package com.bringbackdada.site.bootstrap;

import com.bringbackdada.site.model.*;
import com.bringbackdada.site.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class loads all tables with initial null values of all entries at index 1.
 * NOTE: Index 1 is always the default "empty" entry if none otherwise given and all methods
 * thus presume that actual data ALWAYS starts with id index 2.*5
 * IMPORTANT! This class must always load first, before adding any other data to the database.
 * @since 0.0.1
 */
@Component("datainitializer")
public class DataInitializer {

    private final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final CreatorServiceImpl creatorService;
    private final GalleryServiceImpl galleryService;
    private final ModelServiceImpl modelService;
    private final LicenseServiceImpl licenseService;
    private final ContentServiceImpl contentService;
    private final BlogServiceImpl blogService;
    private final TagServiceImpl tagService;
    private final ProjectServiceImpl projectService;

    public DataInitializer(CreatorServiceImpl creatorService,
                           GalleryServiceImpl galleryService,
                           ModelServiceImpl modelService,
                           LicenseServiceImpl licenseService,
                           ContentServiceImpl contentService,
                           BlogServiceImpl blogService,
                           TagServiceImpl tagService,
                           ProjectServiceImpl projectService) {
        this.creatorService = creatorService;
        this.galleryService = galleryService;
        this.modelService = modelService;
        this.licenseService = licenseService;
        this.contentService = contentService;
        this.blogService = blogService;
        this.tagService = tagService;
        this.projectService = projectService;
        // initializeDefaultTestEntries(); // TODO for dev only!!!
        // initializeDefaultDatabase();
    }

    private void initializeDefaultDatabase() {
        /* CREATOR */
        Creator publicCreator = new Creator();
        publicCreator.setName("Public");
        publicCreator.setDescription("This resource is in the Public Domain and has no particular license.");
        creatorService.save(publicCreator);

        Creator silenceisgrandCreator = new Creator();
        silenceisgrandCreator.setName("Silenceisgrand");
        silenceisgrandCreator.setDescription("Silenceisgrand is a Berlin-based photographer and visual artist.");
        creatorService.save(silenceisgrandCreator);

        Creator mydogspiesCreator = new Creator();
        mydogspiesCreator.setName("Mydogspies");
        mydogspiesCreator.setDescription("Berlin-based software and content developer");
        creatorService.save(mydogspiesCreator);


        /* LICENSE */
        License license = new License();
        license.setCategory(LicenseCategory.PUBLIC_DOMAIN);
        license.setShortDescription("No permission or license is required for a work in the public domain, such as one with an expired copyright. Such work may be used in any context.");
        license.setUrl("https://wiki.creativecommons.org/wiki/public_domain");
        licenseService.save(license);

        License byNcLicense = new License();
        byNcLicense.setCategory(LicenseCategory.CC);
        byNcLicense.setShortDescription("Attribution-NonCommercial 4.0 International (CC BY-NC 4.0); You are free to:\n" +
                "    Share — copy and redistribute the material in any medium or format & " +
                "    Adapt — remix, transform, and build upon the material ");
        byNcLicense.setUrl("https://creativecommons.org/licenses/by-nc/4.0/");
        licenseService.save(byNcLicense);

        /* MODEL */
        Model model = new Model();
        model.setName("Louise");
        model.setDescription("");
        modelService.save(model);

        /* TAGS */
        Tag tag1 = new Tag();
        tag1.setTag("photography");
        tagService.save(tag1);

        Tag tag2 = new Tag();
        tag2.setTag("nude");
        tagService.save(tag2);

        Tag tag3 = new Tag();
        tag3.setTag("fine art photography");
        tagService.save(tag3);

        Tag tag4 = new Tag();
        tag4.setTag("abstract");
        tagService.save(tag4);

        Set<Tag> tags = new HashSet<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);

        /* DEFAULT "ZERO" CONTENT */
        Content zeroContent = new Content();
        contentService.save(zeroContent);

        logger.info("Default entries initialized in the database");
    }
}
