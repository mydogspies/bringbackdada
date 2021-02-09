package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findById(Long id);

    Iterable<Tag> findAll();
}
