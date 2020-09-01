package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Blog;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BlogRepository extends CrudRepository<Blog, Long> {

    Optional<Blog> findById(Long id);

    Iterable<Blog> findAll();
}
