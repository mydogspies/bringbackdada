package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.CreatorService;
import com.bringbackdada.site.services.LicenseService;
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

class AdminAddContentControllerTest {

    AdminAddContentController adminAddContentController;

    @Mock
    CreatorService creatorService;

    @Mock
    LicenseService licenseService;

    @Mock
    ContentService contentService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.adminAddContentController = new AdminAddContentController(creatorService, licenseService, contentService);
    }

    @Test
    void getAddContentPageTitle() {
        String returnUrl = adminAddContentController.getAddContentPage(model);
        verify(model,times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
        assertEquals(returnUrl, "add-content");
    }
}