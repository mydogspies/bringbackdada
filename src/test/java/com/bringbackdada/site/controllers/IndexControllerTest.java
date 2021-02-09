package com.bringbackdada.site.controllers;

import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.services.ContentService;
import com.bringbackdada.site.services.GalleryItemService;
import com.bringbackdada.site.services.GalleryService;
import com.bringbackdada.site.services.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    IndexController controller;

    @Mock
    GalleryService mockGalleryService;

    @Mock
    ContentToContentCmd contentToContentCmd;

    @Mock
    GalleryItemService galleryItemService;

    @Mock
    ImageService imageService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new IndexController(mockGalleryService, galleryItemService, imageService, contentToContentCmd);
    }

    @Test
    void getEntryInputPageAndCheckTitleTextAttribute() {

        // TODO implement new GalleryItem
        List<Gallery> galleryList = new ArrayList<>();
        // List<Content> contentList = new ArrayList<>();
        Gallery gallery = new Gallery();
        Content content = new Content();
        // contentList.add(content);
        // gallery.setContent(contentList);
        galleryList.add(gallery);

        when(mockGalleryService.getGalleryByFeatured()).thenReturn(galleryList);

        String returnUrl = controller.getIndexPage(model);

        assertEquals("home", returnUrl);
        verify(model, times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
    }

}