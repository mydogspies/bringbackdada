package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.repositories.CreatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreatorServiceImplTest {

    CreatorServiceImpl creatorService;

    @Mock
    CreatorRepository creatorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        creatorService = new CreatorServiceImpl(creatorRepository);
    }

    @Test
    void findAll() {
        Creator creator = new Creator();
        HashSet<Creator> creatorSet = new HashSet<>();
        creatorSet.add(creator);

        when(creatorRepository.findAll()).thenReturn(creatorSet);

        Set<Creator> creators = creatorService.findAll();

        assertEquals(creators.size(), 1);
        verify(creatorRepository, times(1)).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void count() {
    }
}