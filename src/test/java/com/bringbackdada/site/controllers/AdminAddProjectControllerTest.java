package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.converters.BlogToBlogCmd;
import com.bringbackdada.site.commands.converters.CreatorToCreatorCmd;
import com.bringbackdada.site.commands.converters.GalleryToGalleryCmd;
import com.bringbackdada.site.commands.converters.TagToTagCmd;
import com.bringbackdada.site.services.*;
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

class AdminAddProjectControllerTest {

    AdminAddProjectController controller;

    @Mock
    Model model;

    @Mock
    GalleryService galleryService;

    @Mock
    CreatorService creatorService;

    @Mock
    BlogService blogService;

    @Mock
    ProjectService projectService;

    @Mock
    TagService tagService;

    @Mock
    GalleryToGalleryCmd galleryToGalleryCmd;

    @Mock
    CreatorToCreatorCmd creatorToCreatorCmd;

    @Mock
    BlogToBlogCmd blogToBlogCmd;

    @Mock
    TagToTagCmd tagToTagCmd;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AdminAddProjectController(
                galleryService,
                creatorService,
                blogService,
                projectService,
                tagService,
                galleryToGalleryCmd,
                creatorToCreatorCmd,
                blogToBlogCmd, tagToTagCmd);
    }

    @Test
    void showAddBlogPage() {
        String returnUrl = controller.showAddProjectPage(model);
        verify(model, times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
        assertEquals(returnUrl, "add-project");
    }
}