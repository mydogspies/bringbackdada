package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Gallery;

import java.util.Optional;

public interface GalleryService extends CrudService<Gallery, Long> {

    Gallery getGalleryByFeatured();

}
