package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryService;
import com.bringbackdada.site.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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
    UserService userService;

    @MockBean
    private ContentService contentService;

    @MockBean
    private GalleryService galleryService;

    @Test
    @WithMockUser(username = "admin", password = "$2y$12$LoUoUX4t54/gDGDNvNf6w.hBhpVffPdoI3lZG0P0bVvEnBRVCsw5i")
    void showAddGalleryPageResponse() throws Exception {
        mockMvc.perform(get("/admin/add-new-gallery"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-gallery"));
    }

    // TODO add test for saveOrUpdateGallery()
}