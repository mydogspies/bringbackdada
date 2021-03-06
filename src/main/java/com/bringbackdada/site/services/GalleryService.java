package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.model.Gallery;

import java.util.List;

public interface GalleryService extends CrudService<Gallery, Long> {

    List<Gallery> getGalleryByFeatured();
    List<Gallery> sortGalleryByGalleryOrder(List<Gallery> unsortedList);
    GalleryCommand saveGalleryCommand(GalleryCommand command);
    List<GalleryCommand> findAllAsCommands();
}
