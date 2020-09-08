package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.GalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    IndexController controller;

    @Mock
    GalleryService mockGalleryService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new IndexController(mockGalleryService);
    }

    @Test
    void getEntryInputPageAndCheckTitleTextAttribute() {
        String returnUrl = controller.getIndexPage(model);

        assertEquals("home", returnUrl);
        verify(model, times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
    }

}