package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Tags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;

public interface TagsRepository extends CrudRepository<Tags, Long> {

    Optional<Tags> findById(Long id);
}
