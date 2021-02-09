package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ContentToContentCmdTest {

    ContentToContentCmd converter;

    @Mock
    CreatorToCreatorCmd creatorConverter;

    @Mock
    ModelToModelCmd modelConverter;

    @Mock
    TagToTagCmd tagConverter;

    @BeforeEach
    void setUp() {
        converter = new ContentToContentCmd(creatorConverter, modelConverter, tagConverter);
    }

    @Test
    void emptyObjectTest() {
        assertNotNull(converter.convert((new Content())));
    }

    @Test
    void convert() {
    }
}