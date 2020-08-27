package com.bringbackdada.site.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BlogTest {

    Blog blog;
    Creator creator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.blog = new Blog();
        this.creator = new Creator();
    }

    @Test
    void getId() {
        Long id = 23L;
        blog.setId(id);
        assertEquals(id, blog.getId());
    }

    @Test
    void getDate() {
        Instant now = Instant.now();
        blog.setMilliseconds(now);
        assertEquals(now, blog.getMilliseconds());
    }

    @Test
    void getEntryName() {
        String entryName = "Long nice entry name for this blog with %&/special 'characters'";
        blog.setEntryName(entryName);
        assertEquals(entryName, blog.getEntryName());
    }

    @Test
    void getEntryContent() {
        String entryContent = "Long nice entry CONTENT for this blog with %&/)(%§ special 'characters'";
        blog.setEntryContent(entryContent);
        assertEquals(entryContent, blog.getEntryContent());
    }

    @Test
    void getCreator() {
        blog.setCreator(creator);
        assertEquals(creator, blog.getCreator());
    }

    @Test
    void getCategory() {
        HashSet<ContentCategory> categorySet = new HashSet<>();
        categorySet.add(ContentCategory.IMAGE);
        categorySet.add(ContentCategory.VIDEO);
        blog.setCategory(categorySet);
        assertEquals(categorySet, blog.getCategory());
    }
}