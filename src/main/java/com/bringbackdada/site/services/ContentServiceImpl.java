package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.converters.ContentCmdToContent;
import com.bringbackdada.site.commands.converters.ContentToContentCmd;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.repositories.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContentServiceImpl implements ContentService {

    private final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    private final ContentRepository contentRepository;
    private final ContentCmdToContent contentCmdToContent;
    private final ContentToContentCmd contentToContentCmd;

    public ContentServiceImpl(ContentRepository contentRepository, ContentCmdToContent contentCmdToContent, ContentToContentCmd contentToContentCmd1) {
        this.contentRepository = contentRepository;
        this.contentCmdToContent = contentCmdToContent;
        this.contentToContentCmd = contentToContentCmd1;
    }

    @Override
    public ContentCommand saveContentCommand(ContentCommand command) {
        Content detachedContent = contentCmdToContent.convert(command);
        Content savedContent = contentRepository.save(detachedContent); // TODO fix this line
        logger.debug("Content detached from cmd object and saved as id: " + savedContent.getId());
        return contentToContentCmd.convert(savedContent);
    }

    @Override
    public List<Content> sortContentByContentOrder(List<Content> sortedList) {
        List<Content> sortedOrder = sortedList.stream()
                .sorted(Comparator.comparing(Content::getContentOrder))
                .collect(Collectors.toList());
        return sortedOrder;
    }

    @Override
    public List<Content> findAll() {
        Iterable<Content> result = contentRepository.findAll();
        List<Content> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        return resultSet;
    }

    @Override
    public List<ContentCommand> findAllAsCommands() {
        List<ContentCommand> contentCommandList = new ArrayList<>();
        Iterable<Content> result = contentRepository.findAll();
        List<Content> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        for (Content content : resultSet) {
            contentCommandList.add(contentToContentCmd.convert(content));
        }
        return contentCommandList;
    }

    @Override
    public Content findById(Long aLong) {
        Optional<Content> content = contentRepository.findById(aLong);
        // TODO add isPresent() exception
        return content.get();
    }

    @Override
    public Content save(Content object) {
        contentRepository.save(object);
        return object;
    }

    @Override
    public void delete(Content object) {

    }

    @Override
    public void deleteById(Long aLong) {
        contentRepository.deleteById(aLong);
    }

    @Override
    public int count(List<Content> set) {
        return 0;
    }
}
