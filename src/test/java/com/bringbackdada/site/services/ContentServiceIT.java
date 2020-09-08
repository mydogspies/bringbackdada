package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.converters.ContentCmdToContent;
import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContentServiceIT {

    @Autowired
    ContentService contentService;

    @Autowired
    ContentToContentCmd contentToContentCmd;

    @Autowired
    ContentCmdToContent contentCmdToContent;

    @BeforeEach
    void setUp() {
    }

    @Transactional
    @Test
    void saveContentCommandTest() {

        Iterable<Content> content = contentService.findAll();
        Content testContent = content.iterator().next();
        ContentCommand testContentCmd = contentToContentCmd.convert(testContent);

        testContentCmd.setDescription("hello");
        ContentCommand saveContentCommand = contentService.saveContentCommand(testContentCmd);

        assertEquals("hello", saveContentCommand.getDescription());
    }
}