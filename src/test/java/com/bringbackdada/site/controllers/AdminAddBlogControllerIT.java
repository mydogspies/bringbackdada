package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = AdminAddBlogController.class)
class AdminAddBlogControllerIT {

    @MockBean
    UserService userService;

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
    @WithMockUser(username = "admin", password = "$2y$12$LoUoUX4t54/gDGDNvNf6w.hBhpVffPdoI3lZG0P0bVvEnBRVCsw5i")
    void getAddBlogPageResponse() throws Exception {
        mockMvc.perform(get("/admin/add-new-blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-blog"));
    }
}