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

class IndexControllerTest {

    IndexController controller;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new IndexController();
    }

    // TODO implemenet MVC test with Junit5

//    @Test
//    void testController() {
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//        mockMvc.perform(get("/"))
//    }

    @Test
    void getEntryInputPageAndCheckTitleTextAttribute() {
        String returnUrl = controller.getIndexPage(model);

        assertEquals("index", returnUrl);
        verify(model, times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
    }
}