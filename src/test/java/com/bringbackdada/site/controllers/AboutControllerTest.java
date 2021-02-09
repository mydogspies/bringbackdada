package com.bringbackdada.site.controllers;

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

class AboutControllerTest {

    AboutController controller;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AboutController();
    }

    @Test
    void getAboutPageTitle() {
        String returnUrl = controller.getAboutPage(model);
        verify(model,times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
        assertEquals(returnUrl, "about");
    }
}