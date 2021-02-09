package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.GalleryItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GalleryItemRepository extends CrudRepository<GalleryItem, Long> {

    Iterable<GalleryItem> findAll();
    Optional<GalleryItem> findById(Long id);
}
