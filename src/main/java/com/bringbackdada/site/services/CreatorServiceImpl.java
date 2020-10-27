package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.commands.converters.CreatorToCreatorCmd;
import com.bringbackdada.site.model.Creator;
import com.bringbackdada.site.repositories.CreatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CreatorServiceImpl implements CreatorService {

    private final Logger logger = LoggerFactory.getLogger(CreatorServiceImpl.class);

    private final CreatorRepository creatorRepository;
    private final CreatorToCreatorCmd creatorToCreatorCmd;

    public CreatorServiceImpl(CreatorRepository creatorRepository, CreatorToCreatorCmd creatorToCreatorCmd) {
        this.creatorRepository = creatorRepository;
        this.creatorToCreatorCmd = creatorToCreatorCmd;
    }

    @Override
    public List<Creator> findAll() {
        Iterable<Creator> result = creatorRepository.findAll();
        List<Creator> resultSet = StreamSupport.stream(result.spliterator(), false)
                        .collect(Collectors.toList());
        return resultSet;
    }

    @Override
    public List<CreatorCommand> findAllAsCommands() {
        List<CreatorCommand> creatorCmdList = new ArrayList<>();
        Iterable<Creator> result = creatorRepository.findAll();
        List<Creator> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        for (Creator creator : resultSet) {
            creatorCmdList.add(creatorToCreatorCmd.convert(creator));
        }
        return creatorCmdList;
    }

    @Override
    public Creator findById(Long aLong) {
        Optional<Creator> creator = creatorRepository.findById(aLong);

        if (creator.isEmpty()) {
            logger.error("findById(): No such creator found with id " + aLong);
            throw new RuntimeException("Creator not found");
        }

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
