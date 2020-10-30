package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.model.GalleryOld;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerIT {

    IndexController controller;

    @Mock
    GalleryService mockGalleryService;

    @Mock
    ContentService contentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new IndexController(mockGalleryService, contentService);
    }

    @Test
    void getIndexPageResponse() throws Exception {

        // TODO implement new GalleryItem code
        List<Gallery> galleryList = new ArrayList<>();
        // List<Content> contentList = new ArrayList<>();
        Gallery gallery = new Gallery();
        Content content = new Content();
        // contentList.add(content);
        // gallery.setContent(contentList);
        galleryList.add(gallery);

        when(mockGalleryService.getGalleryByFeatured()).thenReturn(galleryList);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}