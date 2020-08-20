package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Iterable<Project> findAll();

    Optional<Project> findById(Long id);
}
