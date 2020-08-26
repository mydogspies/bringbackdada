package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.repositories.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentService;

    public ContentServiceImpl(ContentRepository contentService) {
        this.contentService = contentService;
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
        return null;
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
