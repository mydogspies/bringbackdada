package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Gallery;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GalleryRepository extends CrudRepository<Gallery, Long> {

    Optional<Gallery> findById(Long id);
}
