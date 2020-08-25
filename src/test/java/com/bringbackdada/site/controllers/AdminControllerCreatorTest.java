package com.bringbackdada.site.controllers;

import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.services.CreatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AdminControllerCreatorTest {

    AdminController controller;

    @Mock
    CreatorService creatorService;

    @Mock
    Model model;

    @Captor
    ArgumentCaptor<Set<Creator>> argument;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AdminController(creatorService);
    }

    @Test
    void getEntryInputPageAndCheckForReturn() {
        String returnUrl = controller.getEntryInputPage(model);
        verify(model, times(1)).addAttribute(eq("creatorSet"), Mockito.isA(Set.class));
        assertEquals(returnUrl, "add_db_entries");

    }

    @Test
    void getEntryInputPageAndCheckCreatorSetAttribute() {
        controller.getEntryInputPage(model);

        Creator creator = new Creator();
        Set<Creator> creatorSet = new HashSet<>();
        creatorSet.add(creator);

        when(creatorService.findAll()).thenReturn(creatorSet);
//        argument = ArgumentCaptor.forClass(Set.class);

        // TODO implement argumentcaptor

        verify(model, times(1)).addAttribute(eq("creatorSet"), Mockito.any(Set.class));

//        Set<Creator> setInContext = argument.getValue();
//        assertEquals(1, setInContext.size());
    }

    @Test
    void getEntryInputPageAndCheckCreatorAttribute() {
        controller.getEntryInputPage(model);
        verify(model, times(1)).addAttribute(eq("creator"), Mockito.isA(Creator.class));
    }

    @Test
    void getEntryInputPageAndCheckTitleTextAttribute() {
        controller.getEntryInputPage(model);
        verify(model, times(1)).addAttribute(eq("title_text"), Mockito.any(String.class));
    }

    @Test
    void onCreatorSubmit() {
    }
}