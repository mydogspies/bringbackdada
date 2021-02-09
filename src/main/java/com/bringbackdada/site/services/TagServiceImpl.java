package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Tag;
import com.bringbackdada.site.repositories.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    private final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagRepository tagsRepository;

    public TagServiceImpl(TagRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public Tag findById(Long aLong) {
        Optional<Tag> tag = tagsRepository.findById(aLong);
        if(tag.isEmpty()) {
            logger.error("findById(): No such tag found with id " + aLong);
            throw new RuntimeException("Tag not found");
        }
        return tag.get();
    }

    @Override
    public Tag save(Tag object) {
        tagsRepository.save(object);
        return object;
    }

    @Override
    public void delete(Tag object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public int count(List<Tag> set) {
        return 0;
    }
}
