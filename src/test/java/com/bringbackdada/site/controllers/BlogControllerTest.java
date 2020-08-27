package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.ContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class BlogControllerTest {

    BlogController blogController;

    @Mock
    BlogService mockBlogService;

    @Mock
    ContentService mockContentService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.blogController = new BlogController(mockBlogService, mockContentService);
    }

    @Test
    void getBlog() {
        String returnUrl = blogController.getBlog(model);
        assertEquals(returnUrl,"blog");
        verify(model, Mockito.times(1)).addAttribute(eq("title_text"), Mockito.anyString());
    }
}