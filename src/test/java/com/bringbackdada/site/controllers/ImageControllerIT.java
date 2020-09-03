package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.CreatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = ImageController.class)
class ImageControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CreatorService creatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.creatorService = mock(CreatorService.class);
    }

    @Test
    void getAddContentPageResponse() throws Exception {
        mockMvc.perform(get("/admin/add-new-content"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-content"));
    }
}