package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.converters.ContentCmdToContent;
import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.repositories.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContentServiceImpl implements ContentService {

    private final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    private final ContentRepository contentService;
    private final ContentCmdToContent contentCmdToContent;
    private final ContentToContentCmd contentToContentCmd;

    public ContentServiceImpl(ContentRepository contentService, ContentCmdToContent contentCmdToContent, ContentToContentCmd contentToContentCmd1) {
        this.contentService = contentService;
        this.contentCmdToContent = contentCmdToContent;
        this.contentToContentCmd = contentToContentCmd1;
    }

    @Override
    @Transactional
    public ContentCommand saveContentCommand(ContentCommand command) {
        Content detachedContent = contentCmdToContent.convert(command);
        Content savedContent = contentService.save(detachedContent); // TODO fix this line
        logger.debug("Content detached from cmd object and saved as id: " + savedContent.getId());
        return contentToContentCmd.convert(savedContent);
    }

    @Override
    public Set<Content> findAll() {
        Iterable<Content> result = contentService.findAll();
        Set<Content> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toSet());
        return resultSet;
    }

    @Override
    public Content findById(Long aLong) {
        Optional<Content> content = contentService.findById(aLong);
        // TODO add isPresent() exception
        return content.get();
    }

    @Override
    public Content save(Content object) {
        contentService.save(object);
        return object;
    }

    @Override
    public void delete(Content object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public int count(Set<Content> set) {
        return 0;
    }
}
