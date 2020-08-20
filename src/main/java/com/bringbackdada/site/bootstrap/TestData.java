package com.bringbackdada.site.bootstrap;

import com.bringbackdada.site.model.*;
import com.bringbackdada.site.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Test data for use during the development phase.
 * Will add content to all tables in a the database.
 * IMPORTANT! DEV ONLY - DISCONTINUE IN PRODUCTION!
 */
@DependsOn("datainitializer")
@Component
public class TestData {

    private final Logger logger = LoggerFactory.getLogger(TestData.class);

    private final BlogRepository blogRepository;
    private final ContentRepository contentRepository;
    private final CreatorRepository creatorRepository;
    private final GalleryRepository galleryRepository;
    private final ModelRepository modelRepository;
    private final ProjectRepository projectRepository;
    private final TagsRepository tagsRepository;
    private final LicenseRepository licenseRepository;

    public TestData(BlogRepository blogRepository,
                    ContentRepository contentRepository,
                    CreatorRepository creatorRepository,
                    GalleryRepository galleryRepository,
                    ModelRepository modelRepository,
                    ProjectRepository projectRepository,
                    TagsRepository tagsRepository,
                    LicenseRepository licenseRepository) {
        this.blogRepository = blogRepository;
        this.contentRepository = contentRepository;
        this.creatorRepository = creatorRepository;
        this.galleryRepository = galleryRepository;
        this.modelRepository = modelRepository;
        this.projectRepository = projectRepository;
        this.tagsRepository = tagsRepository;
        this.licenseRepository = licenseRepository;
        addCreator();
        addModel();
        addLicense();
        addContent();
        addGallery();
        addBlog();
        addProject();
    }

    private void addCreator() {
        Creator creator = new Creator();
        creator.setName("Silenceisgrand");
        creator.setDescription("Berlin based fantastic visual artist");

        creatorRepository.save(creator);
        logger.info("TEST data CREATOR added to database");
    }

    private void addModel() {
        Model model = new Model();
        model.setName("Mayamey");
        model.setDescription("Hottest model in the country!");

        modelRepository.save(model);
        logger.info("TEST data MODEL added to database");
    }

    private void addLicense() {
        License license = new License();
        license.setCategory(LicenseCategory.CC);
        license.setUrl("https://creativecommons.org/licenses/by-sa/2.0/");
        license.setShortDescription(" Share — copy and redistribute the material in any medium or format Adapt — remix, transform, and build upon the material for any purpose, even commercially.");

        licenseRepository.save(license);
        logger.info("TEST data LICENSE added to database");
    }

    private void addContent() {
        Content content = new Content();
        content.setContentTitle("Cool TEST image");

        // CREATOR
        Set<Creator> creatorSet = new HashSet<>();
        Optional<Creator> creatorObject = creatorRepository.findById(2L);
        if (creatorObject.isEmpty()) {
            throw new RuntimeException("Expected CREATOR not found");
        }
        Creator cr = creatorObject.get();
        creatorSet.add(cr);
        content.setCreator(creatorSet);

        // MODEL
        Set<Model> modelSet = new HashSet<>();
        Optional<Model> modelObject = modelRepository.findById(2L);
        if (modelObject.isEmpty()) {
            throw new RuntimeException("Expected MODEL not found");
        }
        Model md = modelObject.get();
        modelSet.add(md);
        content.setModel(modelSet);

        // TAGS
        Set<Tags> tagSet = new HashSet<>();
        Iterable<Tags> tagList = tagsRepository.findAll();
        if (!tagList.iterator().hasNext()) {
            throw new RuntimeException("Expected TAGS not found");
        }
        tagSet.addAll((Collection<? extends Tags>) tagList);
        content.setTags(tagSet);

        // LICENSE
        Optional<License> licenseObject = licenseRepository.findByCategory(LicenseCategory.CC);
        if (licenseObject.isEmpty()) {
            throw new RuntimeException("Expected LICENSE not found");
        }
        License lic = licenseObject.get();
        content.setLicense(lic);

        // MISC
        content.setCategory(ContentCategory.IMAGE);
        content.setContentFile("bbd_20.jpg");
        content.setContentUrl("/images/projects/nudes/");
        content.setDescription("Nicest nude on the planet");

        contentRepository.save(content);
        logger.info("TEST data CONTENT added to database");

    }

    private void addGallery() {
        Gallery gallery = new Gallery();
        Set<Content> conSet = new HashSet<>();
        gallery.setDescription("A magnificent gallery full of fantastic images!");

        Optional<Content> content = contentRepository.findById(2L);
        if (content.isEmpty()) {
            throw new RuntimeException("Expected CONTENT not found");
        }
        conSet.add(content.get());
        gallery.setContent(conSet);

        galleryRepository.save(gallery);
        logger.info("TEST data GALLERY added to database");
    }

    private void addBlog() {
        Blog blog = new Blog();

        blog.setDate(new Date());
        blog.setEntryName("TestBlog");
        blog.setEntryContent("Ecce, xiphias! Try peeling chicken breasts pudding mixed with ice water.");

        Optional<Creator> certCreator = creatorRepository.findById(2L);
        if(certCreator.isEmpty()) {
            throw new RuntimeException("Expected CREATOR (addBlog) not found");
        }
        blog.setCreator(certCreator.get());

        Set<ContentCategory> category = new HashSet<>();
        category.add(ContentCategory.TEXT);
        category.add(ContentCategory.IMAGE);
        blog.setCategory(category);

        blogRepository.save(blog);
        logger.info("TEST data BLOG added to database");
    }

    private void addProject() {
        Project project = new Project();
        project.setName("TEST PROJECT");
        project.setProjectCategory(ProjectCategory.PHOTO);

        // ADD GALLERY
        Optional<Gallery> gallery = galleryRepository.findById(2L);
        if (gallery.isEmpty()) {
            throw new RuntimeException("Expected GALLERY (addProject) not found");
        }
        Set<Gallery> galSet = new HashSet<>();
        galSet.add(gallery.get());
        project.setGallery(galSet);

        // ADD CREATOR
        Optional<Creator> creator = creatorRepository.findById(2L);
        if (creator.isEmpty()) {
            throw new RuntimeException("Expected CREATOR (addProject) not found");
        }
        project.setCreator(creator.get());

        // ADD BLOG
        Optional<Blog> blog = blogRepository.findById(2L);
        if (blog.isEmpty()) {
            throw new RuntimeException("Expected BLOG (addProject) not found");
        }
        Set<Blog> blogSet = new HashSet<>();
        blogSet.add(blog.get());
        project.setBlog(blogSet);

        // DESCRIPTION
        project.setDescription("TEST project for development");

        // TAGS
        Set<Tags> tagsSet = new HashSet<>();
        for (long n = 3L; n<6L; n++) {
            Optional<Tags> tag = tagsRepository.findById(n);
            if (tag.isPresent()) {
                tagsSet.add(tag.get());
            } else {
                throw new RuntimeException("Expected TAG (addProject) not found");
            }

        }
        project.setTags(tagsSet);

        projectRepository.save(project);
        logger.info("TEST data BLOG added to database");
    }
}
