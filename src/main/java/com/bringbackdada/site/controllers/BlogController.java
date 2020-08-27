package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Blog;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The controller for the blog page
 * @author mydogspies@zoho.com
 * @since 0.0.1
 */
@Controller
public class BlogController {

    private final BlogService blogService;
    private final ContentService contentService;

    public BlogController(BlogService blogService, ContentService contentService) {
        this.blogService = blogService;
        this.contentService = contentService;
    }

    private final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @RequestMapping(value={"blog","blog.html"}, method= RequestMethod.GET)
    public String getBlog(Model model){

        Set<Blog> blogSet = blogService.findAll();
        List<Blog> sortedBlogList = sortBlog(blogSet);
        List<Map<String, Object>> thymeleafOutput = formattedBlogOutput(sortedBlogList);

        Map<String, Object> firstEntry = thymeleafOutput.get(0);
        model.addAttribute("firstBlog", firstEntry);
        sortedBlogList.remove(0);

        model.addAttribute("restBlogs", sortedBlogList);

        model.addAttribute("title_text", "Bringbackdada - BLOG");

        logger.info("--> Calling blog.html");
        return "blog";
    }

    /**
     * Takes all the data and makes it into a hashmap ready-formatted to be output by Thymeleaf.
     * @param sortedBlogs
     * @return a list of HashMaps, each being a Blog entry formatted for Thymeleaf.
     */
    private List<Map<String, Object>> formattedBlogOutput(List<Blog> sortedBlogs) {

        List<Map<String, Object>> output = new ArrayList<>();
        Map<String, Object> outputMap = new HashMap<>();

        for (Blog blog : sortedBlogs) {
            outputMap.put("entryName", blog.getEntryName());
            outputMap.put("contentSnippet", blog.getContentSnippet());
            outputMap.put("entryContent", blog.getEntryContent());
            outputMap.put("creator", blog.getCreator().getName());
            outputMap.put("category", blog.getCategory());

            Instant instant = blog.getMilliseconds();
            outputMap.put("time", "INSTANT");

            if (!blog.getBlogImageId().equals(1L)) {
                Content content  = contentService.findById(blog.getBlogImageId());
                outputMap.put("imgUrl", content.getContentUrl() + content.getContentFile());
            } else {
                outputMap.put("imgUrl", "#");
            }

            output.add(outputMap);
        }

        return output;
    }

    private List<Blog> sortBlog(Set<Blog> blog) {
        List<Blog> blogList = new ArrayList<>(blog);
        Comparator<Blog> timeSorter = Comparator.comparing(Blog::getMilliseconds);
        blogList.sort(timeSorter);
        return blogList;
    }
}
