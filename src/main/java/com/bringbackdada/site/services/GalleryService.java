package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.model.Gallery;

import java.util.List;
import java.util.Optional;

public interface GalleryService extends CrudService<Gallery, Long> {

    List<Gallery> getGalleryByFeatured();
    GalleryCommand saveGalleryCommand(GalleryCommand command);

}
