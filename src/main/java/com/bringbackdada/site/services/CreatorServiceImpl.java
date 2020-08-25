package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.repositories.CreatorRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CreatorServiceImpl implements CreatorService {

    private final CreatorRepository creatorRepository;

    public CreatorServiceImpl(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @Override
    public Set<Creator> findAll() {
        Iterable<Creator> result = creatorRepository.findAll();

        Set<Creator> resultSet =
                StreamSupport.stream(result.spliterator(), false)
                        .collect(Collectors.toSet());

        return resultSet;
    }

    @Override
    public Creator findById(Integer integer) {
        return null;
    }

    @Override
    public Creator save(Creator object) {
        creatorRepository.save(object);
        return object;
    }

    @Override
    public void delete(Creator object) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public int count(Set<Creator> set) {
        return 0;
    }
}
