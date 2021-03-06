package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.model.GalleryItem;
import com.bringbackdada.site.services.GalleryItemService;
import com.bringbackdada.site.services.GalleryService;
import com.bringbackdada.site.services.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
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
    private final GalleryItemService galleryItemService;
    private final ImageService imageService;
    private final ContentToContentCmd contentToContentCmd;

    public IndexController(GalleryService galleryService, GalleryItemService galleryItemService, ImageService imageService, ContentToContentCmd contentToContentCmd) {
        this.galleryService = galleryService;
        this.galleryItemService = galleryItemService;
        this.imageService = imageService;
        this.contentToContentCmd = contentToContentCmd;
    }

    @GetMapping({"", "/", "index", "index.html"})
    public String getIndexPage(Model model){

        List<Gallery> galleryList = galleryService.getGalleryByFeatured();
        List<ContentCommand> contentList = new ArrayList<>();

        if (!galleryList.isEmpty()) {
            for (Gallery gallery : galleryService.sortGalleryByGalleryOrder(galleryList)) {

                if (gallery.getFrontPageFeatured()) {
                    List<GalleryItem> galleryItemList = galleryItemService
                            .sortGalleryItemByGalleryItemOrder(gallery.getGalleryItem());
                    for (GalleryItem galleryItem : galleryItemList) {
                        if (galleryItem.getVisible() && galleryItem.getContent().getOnFrontPage()) {
                            contentList.add(contentToContentCmd.convert(galleryItem.getContent()));
                        }
                    }
                }

            }
        } else {
            return "error-404";
        }

        logger.debug("contentList = " + contentList);

        model.addAttribute("contentList", contentList);
        model.addAttribute("title_text", "Bringbackdada.com - Fine art photography and composition");

        logger.info("--> Called home.html");
        return "home";
    }

    /**
     * Takes an image id and returns a resized image to the browser as a HttpServletResponse.
     * @param id The unique Id of the Content object which contains the image.
     * @param response HttpServletResponse
     * @throws IOException
     */
    @GetMapping("/gallery/image/thumbs/{id}")
    public void showGalleryThumbImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        int width = 225;
        response.setContentType("image/jpeg");
        InputStream is = imageService.getImageStream(id, width);

        if (is != null) {
            response.setHeader("Cache-Control", "max-age=31556926");
            IOUtils.copy(is, response.getOutputStream());
            is.close();
        }
    }

    /**
     * Takes an image id and returns a full size image to the browser as a HttpServletResponse.
     * NOTE: Setting the width attribute to 0 (zero) will return the original image stream as is in the database.
     * @param id The unique Id of the Content object which contains the image.
     * @param response HttpServletResponse
     * @throws IOException
     */
    @GetMapping("/gallery/image/{id}")
    public void showGalleryFullImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        response.setContentType("image/jpeg");

        InputStream is = imageService.getImageStream(id, 0); // 0 = original width
        if (is != null) {
            response.setHeader("Cache-Control", "max-age=31556926");
            IOUtils.copy(is, response.getOutputStream());
            is.close();
        }
    }
}
