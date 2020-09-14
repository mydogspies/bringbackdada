package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.BlogService;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.CreatorService;
import com.bringbackdada.site.services.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = AdminAddBlogController.class)
class AdminAddBlogControllerIT {

    @MockBean
    ContentService contentService;

    @MockBean
    BlogService blogService;

    @MockBean
    CreatorService creatorService;

    @MockBean
    ImageService imageService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAddBlogPageResponse() throws Exception {
        mockMvc.perform(get("/admin/add-new-blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-blog"));
    }
}