package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, Long> {
}
