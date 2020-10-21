package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Model;
import com.bringbackdada.site.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    @Override
    public List<Model> findAll() {
        Iterable<Model> result = modelRepository.findAll();
        List<Model> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        return resultSet;
    }

    @Override
    public Model findById(Integer integer) {
        return null;
    }

    @Override
    public Model save(Model object) {
        modelRepository.save(object);
        return object;
    }

    @Override
    public void delete(Model object) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public int count(List<Model> set) {
        return 0;
    }
}
