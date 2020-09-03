package com.bringbackdada.site.controllers;

import com.bringbackdada.site.exceptions.NotFoundException;
import com.bringbackdada.site.model.Blog;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    public BlogController(BlogService blogService, ContentService contentService) {
        this.blogService = blogService;
        this.contentService = contentService;
    }

    @RequestMapping(value = {"photography-blog", "photography-blog.html"}, method = RequestMethod.GET)
    public String getBlog(Model model) {

        Set<Blog> blogSet = blogService.findAll();

        if (!blogSet.isEmpty()) {
            List<Blog> sortedBlogList = sortBlog(blogSet);

            List<Map<String, Object>> thymeFormattedOutput = thymeOutput(sortedBlogList);

            Map<String, Object> firstEntry = thymeFormattedOutput.get(0);
            model.addAttribute("firstBlog", firstEntry);
            thymeFormattedOutput.remove(0);

            model.addAttribute("restBlogs", thymeFormattedOutput);
            model.addAttribute("title_text", "Bringbackdada | The photography and art blog");

            logger.info("--> Calling blog.html");
            return "blog";
        } else {
            throw new NotFoundException("getBlog(): No blogs returned from database");
        }
    }

    private List<Map<String, Object>> thymeOutput(List<Blog> blogList) {

        List<Map<String, Object>> outputList = new ArrayList<>();

        for (Blog blog : blogList) {
            Map<String, Object> outputMap = new HashMap<>();

            outputMap.put("entryName", blog.getEntryName());
            outputMap.put("entryContent", blog.getEntryContent());
            outputMap.put("contentSnippet", blog.getContentSnippet());
            outputMap.put("creator", blog.getCreator());

            // TODO implement a format of Instant
            Instant instant = blog.getMilliseconds();
            String datetime = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of("UTC")).format(instant);
            outputMap.put("time", datetime);

            if (!blog.getBlogImageId().equals(1L)) {
                Long imgId = blog.getBlogImageId();
                Content content = contentService.findById(imgId);
                outputMap.put("imgUrl", "/images" + content.getContentUrl() + "/" + content.getContentFile());
            } else {
                outputMap.put("imgUrl", "n/a");
            }

            outputList.add(outputMap);
        }
        return outputList;
    }

    // TODO verify sort order and functionality with real life data
    private List<Blog> sortBlog(Set<Blog> blog) {
        List<Blog> blogList = new ArrayList<>(blog);
        Comparator<Blog> timeSorter = Comparator.comparing(Blog::getMilliseconds);
        blogList.sort(timeSorter);
        return blogList;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView dataNotFound404(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");

        model.addAttribute("title_text", "Bringbackdada | No blogs found");

        return modelAndView;
    }
}
