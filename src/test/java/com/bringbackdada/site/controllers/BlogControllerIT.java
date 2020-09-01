package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Blog;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.ContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class BlogControllerIT {

    BlogController blogController;

    @Mock
    BlogService mockBlogService;

    @Mock
    ContentService mockContentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.blogController = new BlogController(mockBlogService, mockContentService);
    }

    @Test
    public void getBlogTest() throws Exception {

        Blog blog = new Blog();
        blog.setId(1L);
        blog.setMilliseconds(Instant.now());

        Set<Blog> blogSet = new HashSet<>();
        blogSet.add(blog);

        Content content = new Content();
        content.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();

        when(mockBlogService.findAll()).thenReturn(blogSet);
        when(mockContentService.findById(anyLong())).thenReturn(content);

        mockMvc.perform(get("/photography-blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"));

    }
}