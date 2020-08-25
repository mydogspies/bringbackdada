package com.bringbackdada.site.bootstrap;

import com.bringbackdada.site.model.*;
import com.bringbackdada.site.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

    private final BlogRepository blogRepository;
    private final ContentRepository contentRepository;
    private final CreatorRepository creatorRepository;
    private final GalleryRepository galleryRepository;
    private final LicenseRepository licenseRepository;
    private final ModelRepository modelRepository;
    private final ProjectRepository projectRepository;
    private final TagsRepository tagsRepository;

    public DataInitializer(BlogRepository blogRepository,
                           ContentRepository contentRepository,
                           CreatorRepository creatorRepository,
                           GalleryRepository galleryRepository,
                           LicenseRepository licenseRepository,
                           ModelRepository modelRepository,
                           ProjectRepository projectRepository,
                           TagsRepository tagsRepository) {
        this.blogRepository = blogRepository;
        this.contentRepository = contentRepository;
        this.creatorRepository = creatorRepository;
        this.galleryRepository = galleryRepository;
        this.licenseRepository = licenseRepository;
        this.modelRepository = modelRepository;
        this.projectRepository = projectRepository;
        this.tagsRepository = tagsRepository;
        initializeDefaultNullEntries();
    }

    private void initializeDefaultNullEntries() {
        Creator creator = new Creator();
        Gallery gallery = new Gallery();
        Model model = new Model();
        License license = new License();
        Content content = new Content();
        Project project = new Project();
        Blog blog = new Blog();
        Tags tags = new Tags();

        creatorRepository.save(creator);
        galleryRepository.save(gallery);
        modelRepository.save(model);
        licenseRepository.save(license);
        contentRepository.save(content);
        projectRepository.save(project);
        blogRepository.save(blog);
        tagsRepository.save(tags);

        logger.info("Default NULL entries initialized in the database");
    }

}
