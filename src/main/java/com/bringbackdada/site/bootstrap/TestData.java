package com.bringbackdada.site.bootstrap;

import com.bringbackdada.site.model.*;
import com.bringbackdada.site.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Test data for use during the development phase.
 * Will add content to all tables in a the database.
 * IMPORTANT! DEV ONLY - DISCONTINUE IN PRODUCTION!
 */
@DependsOn("datainitializer")
@Component
public class TestData {

    private final Logger logger = LoggerFactory.getLogger(TestData.class);

    private BlogRepository blogRepository;
    private ContentRepository contentRepository;
    private CreatorRepository creatorRepository;
    private GalleryRepository galleryRepository;
    private ModelRepository modelRepository;
    private ProjectRepository projectRepository;
    private TagsRepository tagsRepository;

    public TestData(BlogRepository blogRepository,
                    ContentRepository contentRepository,
                    CreatorRepository creatorRepository,
                    GalleryRepository galleryRepository,
                    ModelRepository modelRepository,
                    ProjectRepository projectRepository,
                    TagsRepository tagsRepository) {
        this.blogRepository = blogRepository;
        this.contentRepository = contentRepository;
        this.creatorRepository = creatorRepository;
        this.galleryRepository = galleryRepository;
        this.modelRepository = modelRepository;
        this.projectRepository = projectRepository;
        this.tagsRepository = tagsRepository;
    }

//    private void addCreator() {
//        Creator creator = new Creator();
//        creator.setName("Silenceisgrand");
//        creator.setDescription("Berlin based fantastic visual artist");
//        creator.setContent();
//    }
//
//    private void addGallery() {
//        Gallery gallery = new Gallery();
//        gallery.setContent();
//        gallery.setProject();
//        gallery.setDescription();
//    }
//
//    private void addModel() {
//        Model model = new Model();
//        model.setName();
//        model.setDescription();
//        model.setContent();
//    }
//
//    private void addLicense() {
//        License license = new License();
//        license.setCategory();
//        license.setUrl();
//        license.setShortDescription();
//    }
//
//    private void addContent() {
//        Content content = new Content();
//        content.setContentTitle("Cool TEST image");
//        content.setCreatorId();
//        content.setModelId();
//        content.setGallery();
//        content.setCategory();
//        content.setContentFile();
//        content.setContentUrl();
//        content.setDescription();
//        content.setTags();
//    }
//
//    private void addProject() {
//        Project project = new Project();
//        project.setProjectCategory();
//        project.setGallery();
//        project.setCreator();
//        project.setBlog();
//        project.setDescription();
//    }

    private void addBlog() {
        Blog blog = new Blog();
        blog.setDate(new Date());
        blog.setEntryName("TestBlog");
        blog.setEntryContent("Ecce, xiphias! Try peeling chicken breasts pudding mixed with ice water.");
        Set<ContentCategory> category = new HashSet<ContentCategory>();
        category.add(ContentCategory.TEXT);
        category.add(ContentCategory.IMAGE);
        blog.setCategory(category);
        blogRepository.save(blog);
        logger.info("TEST Blog data created and saved");
    }
}
