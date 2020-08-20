package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Tags;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagsRepository extends CrudRepository<Tags, Long> {

    Optional<Tags> findById(Long id);

    Iterable<Tags> findAll();
}
