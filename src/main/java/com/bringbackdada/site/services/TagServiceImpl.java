package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Tag;
import com.bringbackdada.site.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

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
        return null;
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
