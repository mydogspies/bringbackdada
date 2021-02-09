package com.bringbackdada.site.services;

import com.bringbackdada.site.model.GalleryItem;

import java.util.List;

public interface GalleryItemService extends CrudService<GalleryItem, Long>{

    List<GalleryItem> sortGalleryItemByGalleryItemOrder(List<GalleryItem> unsortedList);
}
