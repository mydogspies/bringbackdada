package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.CreatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdminAddBlogControllerTest {

    AdminAddBlogController controller;

    @Mock
    ContentService contentService;

    @Mock
    BlogService blogService;

    @Mock
    CreatorService creatorService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AdminAddBlogController(contentService, blogService, creatorService);
    }

    @Test
    void showAddBlogPage() {
        String returnUrl = controller.showAddBlogPage(model);
        verify(model, times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
        assertEquals(returnUrl, "add-blog");
    }
}