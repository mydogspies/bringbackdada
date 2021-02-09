package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Creator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CreatorRepository extends CrudRepository<Creator, Long> {

    Optional<Creator> findById(Long id);
}
