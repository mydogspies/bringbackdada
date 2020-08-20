package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
