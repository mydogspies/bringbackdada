package com.bringbackdada.site.bootstrap;

import com.bringbackdada.site.model.*;
import com.bringbackdada.site.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
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
        initializeDefaultDatabase();
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


    // TODO should not be used for production
    /* TEST ONLY */
    private void initializeDefaultTestEntries() {
        /* CREATOR */
        Creator publicCreator = new Creator();
        publicCreator.setName("Public");
        publicCreator.setDescription("This resource is in the Public Domain and has no particular license.");
        creatorService.save(publicCreator);

        Creator KFSchinkelCreator = new Creator();
        KFSchinkelCreator.setName("Karl Friedrich Schinkel");
        KFSchinkelCreator.setDescription("German painter and architect.");
        creatorService.save(KFSchinkelCreator);

        Creator SilenceisgrandCreator = new Creator();
        SilenceisgrandCreator.setName("Silenceisgrand");
        SilenceisgrandCreator.setDescription("Silenceisgrand is a Berlin-based visual artist.");
        creatorService.save(SilenceisgrandCreator);

        /* GALLERY */
        Gallery paintingGallery = new Gallery();
        Set<Content> contentGallerySet = new HashSet<>();
        paintingGallery.setContent(contentGallerySet);
        paintingGallery.setDescription("Karl Friedrich Schinkel - some paintings");
        paintingGallery.setFeatured(true);
        galleryService.save(paintingGallery);

        Gallery buildingGallery = new Gallery();
        Set<Content> buildingGallerySet = new HashSet<>();
        buildingGallery.setContent(buildingGallerySet);
        buildingGallery.setDescription("Karl Friedrich Schinkel - some buildings");
        galleryService.save(buildingGallery);


        /* MODEL */
        Model model = new Model();
        model.setName("Karl Friedrich Schinkel");
        model.setDescription("Famous architect and painter");
        modelService.save(model);

        /* LICENSE */
        License license = new License();
        license.setCategory(LicenseCategory.PUBLIC_DOMAIN);
        license.setShortDescription("No permission or license is required for a work truly in the public domain, such as one with an expired copyright. Such work may be used at will.");
        license.setUrl("https://wiki.creativecommons.org/wiki/public_domain");
        licenseService.save(license);

        /* TAGS */
        Tag tag1 = new Tag();
        tag1.setTag("Schinkel");
        tagService.save(tag1);

        Tag tag2 = new Tag();
        tag2.setTag("Painting");
        tagService.save(tag2);

        Tag tag3 = new Tag();
        tag3.setTag("Art");
        tagService.save(tag3);

        Tag tag4 = new Tag();
        tag4.setTag("Berlin");
        tagService.save(tag4);

        Set<Tag> tags = new HashSet<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);

        /* DEFAULT "ZERO" CONTENT */
        Content zeroContent = new Content();
        contentService.save(zeroContent);

        /* CONTENT - PAINTING GALLERY */
        Content content = new Content();
        content.setContentTitle("Portrait of Karl Friedrich Schinkel");
        Set<Creator> creators = new HashSet<>();
        creators.add(publicCreator);
        content.setCreator(creators);
        content.setCategory(ContentCategory.IMAGE);
        content.setContentFile("800px-Schinkel1.jpg");
        content.setContentUrl("/test");
        content.setLicense(license);
        content.setDescription("Portrait of painter and architect Karl Friedrich Schinkel");
        contentGallerySet.add(content);
        Set<Model> modelSet = new HashSet<>();
        modelSet.add(model);
        content.setModel(modelSet);
        content.setTags(tags);
        content.setOnFrontPage(false);
        contentService.save(content);

        Content content2 = new Content();
        content2.setContentTitle("Schloß am Strom");
        Set<Creator> creators2 = new HashSet<>();
        creators2.add(KFSchinkelCreator);
        content2.setCreator(creators2);
        content2.setCategory(ContentCategory.IMAGE);
        content2.setContentFile("1280px-Karl_Friedrich_Schinkel_-_Schloß_am_Strom_-_Google_Art_Project.jpg");
        content2.setContentUrl("/test");
        content2.setLicense(license);
        content2.setDescription("Schloß am Strom");
        contentGallerySet.add(content2);
        content2.setTags(tags);
        content2.setOnFrontPage(true);
        contentService.save(content2);

        Content content3 = new Content();
        content3.setContentTitle("Blick auf den Mont Blanc");
        content3.setCreator(creators2);
        content3.setCategory(ContentCategory.IMAGE);
        content3.setContentFile("1813_Schinkel_Blick_auf_den_Mont_Blanc_anagoria.jpg");
        content3.setContentUrl("/test");
        content3.setLicense(license);
        content3.setDescription("Blick auf den Mont Blanc");
        contentGallerySet.add(content3);
        content3.setTags(tags);
        content3.setOnFrontPage(true);
        contentService.save(content3);

        Content content4 = new Content();
        content4.setContentTitle("Mittelalterliche Stadt an einem Fluss");
        content4.setCreator(creators2);
        content4.setCategory(ContentCategory.IMAGE);
        content4.setContentFile("1815_Schinkel_Mittelalterliche_Stadt_an_einem_Fluss_anagoria.jpg");
        content4.setContentUrl("/test");
        content4.setLicense(license);
        content4.setDescription("Mittelalterliche Stadt an einem Fluss");
        contentGallerySet.add(content4);
        content4.setTags(tags);
        content4.setOnFrontPage(true);
        contentService.save(content4);

        Content content5 = new Content();
        content5.setContentTitle("Gotische Kirche auf einem Felsen am Meer");
        content5.setCreator(creators2);
        content5.setCategory(ContentCategory.IMAGE);
        content5.setContentFile("1920px-Karl_Friedrich_Schinkel_-_Gotische_Kirche_auf_einem_Felsen_am_Meer_-_Google_Art_Project.jpg");
        content5.setContentUrl("/test");
        content5.setLicense(license);
        content5.setDescription("Gotische Kirche auf einem Felsen am Meer");
        contentGallerySet.add(content5);
        content5.setTags(tags);
        content5.setOnFrontPage(true);
        contentService.save(content5);

        Content content6 = new Content();
        content6.setContentTitle("Stage set for Mozart's Magic Flute");
        content6.setCreator(creators2);
        content6.setCategory(ContentCategory.IMAGE);
        content6.setContentFile("Karl_Friedrich_Schinkel_-_Stage_set_for_Mozart's_Magic_Flute_-_WGA21001.jpg");
        content6.setContentUrl("/test");
        content6.setLicense(license);
        content6.setDescription("Stage set for Mozart's Magic Flute");
        contentGallerySet.add(content6);
        content6.setTags(tags);
        content6.setOnFrontPage(true);
        contentService.save(content6);

        Content content60 = new Content();
        content60.setContentTitle("Gotischer Dom am Wasser");
        content60.setCreator(creators2);
        content60.setCategory(ContentCategory.IMAGE);
        content60.setContentFile("lossless-page1-1280px-Karl_Friedrich_Schinkel_Gotischer_Dom_am_Wasser.tif.png");
        content60.setContentUrl("/test");
        content60.setLicense(license);
        content60.setDescription("Gotischer Dom am Wasser");
        contentGallerySet.add(content60);
        content60.setTags(tags);
        content60.setOnFrontPage(true);
        contentService.save(content60);

        /* CONTENT - BUILDING GALLERY */
        Content content7 = new Content();
        content7.setContentTitle("Altes Museum & Lustgarten");
        content7.setCreator(creators2);
        content7.setCategory(ContentCategory.IMAGE);
        content7.setContentFile("Berlin_altes_Museum_und_Lustgarten_um_1900.jpg");
        content7.setContentUrl("/test");
        content7.setLicense(license);
        content7.setDescription("The Altes Museum and Lustgarten in Berlin around 1900");
        buildingGallerySet.add(content7);
        content7.setTags(tags);
        content7.setOnFrontPage(true);
        contentService.save(content7);

        Content content8 = new Content();
        content8.setContentTitle("Moskowitischer Entwurf Grundriss");
        content8.setCreator(creators2);
        content8.setCategory(ContentCategory.IMAGE);
        content8.setContentFile("Karl_Friedrich_Schinkel_Moskowitischer_Entwurf_Grundriss.tif.png");
        content8.setContentUrl("/test");
        content8.setLicense(license);
        content8.setDescription("Moskowitischer Entwurf Grundriss");
        buildingGallerySet.add(content8);
        content8.setTags(tags);
        content8.setOnFrontPage(true);
        contentService.save(content8);

        Content content9 = new Content();
        content9.setContentTitle("Konzerthaus in Berlin");
        content9.setCreator(creators2);
        content9.setCategory(ContentCategory.IMAGE);
        content9.setContentFile("Konzerths_3a.png");
        content9.setContentUrl("/test");
        content9.setLicense(license);
        content9.setDescription("Konzerthaus in Berlin");
        buildingGallerySet.add(content9);
        content9.setTags(tags);
        content9.setOnFrontPage(true);
        contentService.save(content9);

        Content content10 = new Content();
        content10.setContentTitle("Nikolaikirche");
        content10.setCreator(creators2);
        content10.setCategory(ContentCategory.IMAGE);
        content10.setContentFile("Pc_150036_Nikolaikirche.jpg");
        content10.setContentUrl("/test");
        content10.setLicense(license);
        content10.setDescription("Nikolaikirche");
        buildingGallerySet.add(content10);
        content10.setTags(tags);
        content10.setOnFrontPage(true);
        contentService.save(content10);

        Content content11 = new Content();
        content11.setContentTitle("Schloss Stolzenfels");
        content11.setCreator(creators2);
        content11.setCategory(ContentCategory.IMAGE);
        content11.setContentFile("Schloss_Stolzenfels_01_Koblenz_2015.jpg");
        content11.setContentUrl("/test");
        content11.setLicense(license);
        content11.setDescription("Schloss Stolzenfels");
        buildingGallerySet.add(content11);
        content10.setTags(tags);
        content11.setOnFrontPage(true);
        contentService.save(content11);

        // re-save with the updated set
        paintingGallery.setContent(contentGallerySet);
        galleryService.save(paintingGallery);
        buildingGallery.setContent(buildingGallerySet);
        galleryService.save(buildingGallery);

        /* BLOG */
        Blog blog = new Blog();
        blog.setMilliseconds(Instant.now());
        blog.setEntryName("The works of Karl Friedrich Schinkel");
        blog.setEntryContent("Schinkel was born in Neuruppin, Margraviate of Brandenburg. When he was six, his father died in the disastrous Neuruppin fire of 1787. He became a student of architect Friedrich Gilly (1772–1800) (the two became close friends) and his father, David Gilly, in Berlin. After returning to Berlin from his first trip to Italy in 1805, he started to earn his living as a painter. When he saw Caspar David Friedrich's painting Wanderer above the Sea of Fog at the 1810 Berlin art exhibition he decided that he would never reach such mastery of painting and turned to architecture.[citation needed] Working for the stage, in 1816 he created a star-spangled backdrop for the appearance of the \"Königin der Nacht\" in Wolfgang Amadeus Mozart's opera The Magic Flute, which is even quoted in modern productions of this perennial piece. After Napoleon's defeat, Schinkel oversaw the Prussian Building Commission. In this position, he was not only responsible for reshaping the still relatively unspectacular city of Berlin into a representative capital for Prussia, but also oversaw projects in the expanded Prussian territories from the Rhineland in the west to Königsberg in the east, such as New Altstadt Church.[2]\n" +
                "\n" +
                "From 1808 to 1817 Schinkel renovated and reconstructed Schloss Rosenau, Coburg, in the Gothic Revival style.[3] He also rebuilt the ruins of Chorin Abbey. ");
        blog.setContentSnippet(" German architect and painter whose Romantic–Classical creations in other related arts made him the leading arbiter of national aesthetic taste in his lifetime.");
        blog.setCreator(SilenceisgrandCreator);
        Set<ContentCategory> contentCategory = new HashSet<>();
        contentCategory.add(ContentCategory.IMAGE);
        blog.setCategory(contentCategory);
        blog.setBlogImageId(2L);
        blogService.save(blog);

        Blog blog2 = new Blog();
        blog2.setMilliseconds(Instant.now());
        blog2.setEntryName("Schinkel's architecture");
        blog2.setEntryContent("His most famous extant buildings are found in and around Berlin. These include the Neue Wache (1816–1818), the National Monument for the Liberation Wars (1818–1821), the Schauspielhaus (1819–1821) at the Gendarmenmarkt, which replaced the earlier theatre that was destroyed by fire in 1817, and the Altes Museum on Museum Island (1823–1830). He also carried out improvements to the Crown Prince's Palace and to Schloss Charlottenburg. Schinkel was also responsible for the interior decoration of a number of private Berlin residences. Although the buildings themselves have long been destroyed, portions of a stairwell from the Weydinger House were able to be rescued and built into the Nicolaihaus on Brüderstr. and its formal dining hall into the Palais am Festungsgraben.[2]");
        blog2.setContentSnippet("Hardly any other architect is as connected to Berlin's buildings and sights as Karl Friedrich Schinkel. Schlossbrücke and Altes Museum are just two of many buildings designed by Schinkel.");
        blog2.setCreator(SilenceisgrandCreator);
        blog2.setCategory(contentCategory);
        blog2.setBlogImageId(10L);
        blogService.save(blog2);

        Blog blog3 = new Blog();
        blog3.setMilliseconds(Instant.now());
        blog3.setEntryName("Charlie Chaplin");
        blog3.setEntryContent("Sir Charles Spencer Chaplin KBE (16 April 1889 – 25 December 1977) was an English comic actor, filmmaker, and composer who rose to fame in the era of silent film. He became a worldwide icon through his screen persona, \"The Tramp\", and is considered one of the most important figures in the history of the film industry. His career spanned more than 75 years, from childhood in the Victorian era until a year before his death in 1977, and encompassed both adulation and controversy. ");
        blog3.setContentSnippet("Charlie Chaplin was a comedic British actor who became one of the biggest stars of the 20th century's silent-film era.");
        blog3.setCreator(SilenceisgrandCreator);
        Set<ContentCategory> category2 = new HashSet<>();
        category2.add(ContentCategory.TEXT);
        blog3.setCategory(category2);
        blogService.save(blog3);

        Blog blog4 = new Blog();
        blog4.setMilliseconds(Instant.now());
        blog4.setEntryName("Pyrrhocoris apterus - also commonly called the Firebug");
        blog4.setEntryContent("The firebug, Pyrrhocoris apterus, is a common insect of the family Pyrrhocoridae. Easily recognizable due to its striking red and black coloration, but may be confused with the similarly coloured though unrelated Corizus hyoscyami (cinnamon bug, squash bug) (see comparison).[1] Pyrrhocoris apterus is distributed throughout the Palaearctic from the Atlantic coast of Europe to northwest China. It has also been reported from the US, Central America and India.[2] It has been reported as recently expanding its distribution northwards into mainland UK and eastward on to the coast of the Mediterranean sea. [3] They are frequently observed to form aggregations, especially as immature forms, with from tens to perhaps a hundred individuals.");
        blog4.setContentSnippet("The Firebug (Pyrrhocoris apterus) is a common insect of the family Pyrrhocoridae and easy to recognize due to its striking coloration. ");
        blog4.setCreator(SilenceisgrandCreator);
        blog3.setCategory(category2);
        blogService.save(blog4);

        /* PROJECT */
        Project project = new Project();
        project.setName("Karl Friedrich Schinkel paintings");
        project.setDescription("A test project - Karl Friedrich Schinkel (13 March 1781 – 9 October 1841) was a Prussian architect, city planner, and painter who also designed furniture and stage sets. Schinkel was one of the most prominent architects of Germany and designed both neoclassical and neogothic buildings. His most famous buildings are found in and around Berlin.");
        project.setProjectCategory(ProjectCategory.PHOTO);
        Set<Gallery> gallerySet = new HashSet<>();
        gallerySet.add(paintingGallery);
        gallerySet.add(buildingGallery);
        project.setGallery(gallerySet);
        project.setCreator(SilenceisgrandCreator);
        Set<Blog> blogSet = new HashSet<>();
        blogSet.add(blog);
        blogSet.add(blog2);
        project.setBlog(blogSet);
        project.setTags(tags);
        projectService.save(project);

        logger.info("Default TEST entries initialized in the database");
    }

}
