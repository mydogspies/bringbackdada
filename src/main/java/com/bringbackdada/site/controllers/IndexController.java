package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the main index page
 * @since 0.0.1
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private final GalleryService galleryService;
    private final ContentService contentService;

    public IndexController(GalleryService galleryService, ContentService contentService) {
        this.galleryService = galleryService;
        this.contentService = contentService;
    }

    @GetMapping({"", "/", "index", "index.html"})
    public String getIndexPage(Model model){

        List<Gallery> galleryList = galleryService.getGalleryByFeatured();
        List<Content> contentList = new ArrayList<>();

        // TODO should be command object instead of model object?

        if (!galleryList.isEmpty()) {
            for (Gallery gallery : galleryService.sortGalleryByGalleryOrder(galleryList)) {

                List<Content> unsortedContentList = new ArrayList<>();
                for (Content content : gallery.getContent()) {
                    if (content.getVisible()) {
                        unsortedContentList.add(content);
                    }
                }
                contentList.addAll(contentService.sortContentByContentOrder(unsortedContentList));

            }
        } else {
            return "404error";
        }

        logger.debug("contentList = " + contentList);

        model.addAttribute("contentList", contentList);
        model.addAttribute("title_text", "Bringbackdada.com - Fine art photography and composition");

        logger.info("--> Called home.html");
        return "home";
    }

    @GetMapping("/gallery/image/{id}")
    public void showGalleryImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        response.setContentType("image/jpeg");

        Content content = contentService.findById(id);

        if (content.getVisible()) {
            InputStream is = new ByteArrayInputStream(content.getImageFile());
            IOUtils.copy(is, response.getOutputStream());
        }

    }
}
