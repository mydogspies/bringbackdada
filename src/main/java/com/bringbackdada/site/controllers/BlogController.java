package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.BlogCommand;
import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.commands.converters.BlogToBlogCmd;
import com.bringbackdada.site.exceptions.NotFoundException;
import com.bringbackdada.site.model.Blog;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.ContentService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The controller for the blog page
 *
 * @since 0.0.1
 */
@Controller
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    private final BlogService blogService;
    private final ContentService contentService;
    private final BlogToBlogCmd blogToBlogCmd;

    public BlogController(BlogService blogService, ContentService contentService, BlogToBlogCmd blogToBlogCmd) {
        this.blogService = blogService;
        this.contentService = contentService;
        this.blogToBlogCmd = blogToBlogCmd;
    }

    @GetMapping(value = {"/site/photography-blog", "/site/photography-blog.html"})
    public String getBlogRoll(Model model) {

        List<Blog> blogList = blogService.findAll();

        if (!blogList.isEmpty()) {
            List<Blog> sortedBlogList = sortBlog(blogList);

            List<Map<String, Object>> thymeFormattedOutput = thymeOutput(sortedBlogList);

            if (thymeFormattedOutput.isEmpty()) {
                logger.warn("getBlogRoll(): No blogs set to visible.");
                throw new NotFoundException("getBlogRoll(): No blogs set to visible in database.");
            }

            Map<String, Object> firstEntry = thymeFormattedOutput.get(0);
            model.addAttribute("firstBlog", firstEntry);
            thymeFormattedOutput.remove(0);

            model.addAttribute("restBlogs", thymeFormattedOutput);
            model.addAttribute("title_text", "Bringbackdada | The photography and art blog");

            logger.info("--> Calling blog.html");
            return "blog";
        } else {
            logger.warn("getBlogRoll(): No blogs returned from database.");
            throw new NotFoundException("getBlogRoll(): No blogs returned from database.");
        }
    }

    @Cacheable("blogs")
    @GetMapping(value = "/blog/{id}")
    public String getBlogEntry(@PathVariable("id") Long id, Model model) {

        BlogCommand blog = blogToBlogCmd.convert(blogService.findById(id));

        if (blog != null && blog.getVisible()) {
            CreatorCommand creator = blog.getCreator();
            Long contentId = blog.getContentId();
            Content content = contentService.findById(contentId);
            String altText = content.getAltText();

            model.addAttribute("contentId", contentId);
            model.addAttribute("title_text", "Bringbackdada | " + blog.getEntryName());
            model.addAttribute("entryName", blog.getEntryName());
            model.addAttribute("text", blog.getEntryContent());
            model.addAttribute("author", creator.getName());
            model.addAttribute("altText", altText);
        }

        logger.info("--> Calling blog-entry.html");
        return "blog-entry";
    }

    @GetMapping("/blog/image/{id}")
    public void showGalleryImage(@PathVariable Long id, HttpServletResponse response, Model model) throws IOException {

        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "max-age=31556926");
        Content content = contentService.findById(id);

        InputStream is = new ByteArrayInputStream(content.getImageFile());
        IOUtils.copy(is, response.getOutputStream());
    }

    private List<Map<String, Object>> thymeOutput(List<Blog> blogList) {

        List<Map<String, Object>> outputList = new ArrayList<>();

        for (Blog blog : blogList) {
            if (blog.getVisible()) {
                Map<String, Object> outputMap = new HashMap<>();
                outputMap.put("entryName", blog.getEntryName());
                outputMap.put("entryContent", blog.getEntryContent());
                outputMap.put("contentSnippet", blog.getContentSnippet());
                outputMap.put("creator", blog.getCreator());
                outputMap.put("id", blog.getId());
                outputMap.put("contentId", blog.getContentId());
                Instant instant = blog.getMilliseconds();
                String datetime = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of("UTC")).format(instant);
                outputMap.put("time", datetime);
                outputList.add(outputMap);
            }
        }
        return outputList;
    }


    // TODO verify sort order and functionality with real life data
    private List<Blog> sortBlog(List<Blog> blog) {
        List<Blog> blogList = new ArrayList<>(blog);
        blogList.removeIf(blg -> blg.getId() == 3L); // removes the placeholder blog related to no content (always id=3)
        Comparator<Blog> timeSorter = Comparator.comparing(Blog::getMilliseconds);
        blogList.sort(timeSorter);
        return blogList;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView dataNotFound404(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error-404");

        model.addAttribute("title_text", "Bringbackdada | No blogs found");

        return modelAndView;
    }
}
