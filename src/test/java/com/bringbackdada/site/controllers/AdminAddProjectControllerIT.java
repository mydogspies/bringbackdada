package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.CreatorService;
import com.bringbackdada.site.services.GalleryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = AdminAddProjectController.class)
class AdminAddProjectControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GalleryService galleryService;

    @MockBean
    CreatorService creatorService;

    @MockBean
    BlogService blogService;

    @Test
    void getAddProjectPageResponse() throws Exception {
        mockMvc.perform(get("/admin/add-new-project"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-project"));
    }
}