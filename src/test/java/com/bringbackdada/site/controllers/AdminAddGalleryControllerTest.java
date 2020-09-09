package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryService;
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

class AdminAddGalleryControllerTest {

    AdminAddGalleryController controller;

    @Mock
    Model model;

    @Mock
    ContentService contentService;

    @Mock
    GalleryService galleryService;

    @Mock
    ContentToContentCmd contentConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AdminAddGalleryController(contentService, galleryService, contentConverter);
    }

    @Test
    void getAddGalleryTitle() {
        String returnUrl = controller.showAddGalleryPage(model);
        verify(model, times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
        assertEquals(returnUrl, "add-gallery");
    }
}