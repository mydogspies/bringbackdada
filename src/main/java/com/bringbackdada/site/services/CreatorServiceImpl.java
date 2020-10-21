package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.repositories.CreatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<Creator> findAll() {
        Iterable<Creator> result = creatorRepository.findAll();
        List<Creator> resultSet = StreamSupport.stream(result.spliterator(), false)
                        .collect(Collectors.toList());
        return resultSet;
    }

    @Override
    public Creator findById(Long aLong) {
        Optional<Creator> creator = creatorRepository.findById(aLong);
        // TODO add isPresent() exception
        return creator.get();
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
    public void deleteById(Long aLong) {
        creatorRepository.deleteById(aLong);
    }

    @Override
    public int count(List<Creator> set) {
        return 0;
    }
}
