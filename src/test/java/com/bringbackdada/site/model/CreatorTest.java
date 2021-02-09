package com.bringbackdada.site.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatorTest {

    Creator creator;

    @BeforeEach
    void setUp() {
        this.creator = new Creator();
    }

    @Test
    void getId() {
        Long id = 2L;
        creator.setId(id);
        assertEquals(id, creator.getId());
    }

    @Test
    void setName() {
        String name = "John N'Johnny Johnson";
        creator.setName(name);
        assertEquals(name, creator.getName());
    }

    @Test
    void setDescription() {
        String description = "Faith at the port that is when cold nanomachines meet. 'Hello', says one; $" +
                "machine is the tag. Over 2309842318746 cases of // - slashes that make up these /n words.";
        creator.setDescription(description);
        assertEquals(description, creator.getDescription());
    }
}