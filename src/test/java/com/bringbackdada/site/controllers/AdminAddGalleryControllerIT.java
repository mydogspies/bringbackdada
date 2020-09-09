package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = AdminAddGalleryController.class)
class AdminAddGalleryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @MockBean
    private GalleryService galleryService;

    @Test
    void showAddGalleryPageResponse() throws Exception {
        mockMvc.perform(get("/admin/add-new-gallery"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-gallery"));
    }

    // TODO add test for saveOrUpdateGallery()
}