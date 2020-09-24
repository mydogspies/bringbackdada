package com.bringbackdada.site.controllers;

import com.bringbackdada.site.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = AdminAddContentController.class)
class AdminAddContentControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    CreatorService creatorService;

    @MockBean
    LicenseService licenseService;

    @MockBean
    ContentService contentService;

    @MockBean
    ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.creatorService = mock(CreatorService.class);
        this.licenseService = mock(LicenseService.class);
        this.contentService = mock(ContentService.class);
        this.imageService = mock(ImageService.class);
    }

    @Test
    @WithMockUser(username = "admin", password = "$2y$12$LoUoUX4t54/gDGDNvNf6w.hBhpVffPdoI3lZG0P0bVvEnBRVCsw5i")
    void getAddContentPageResponse() throws Exception {
        mockMvc.perform(get("/admin/add-new-content"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-content"));
    }
}