package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModelRepository extends CrudRepository<Model, Long> {

    Optional<Model> findById(Long id);
}
