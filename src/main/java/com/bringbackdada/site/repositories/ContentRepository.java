package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Content;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContentRepository extends CrudRepository<Content, Long> {

    Iterable<Content> findAll();

    Optional<Content> findById(Long id);
}
