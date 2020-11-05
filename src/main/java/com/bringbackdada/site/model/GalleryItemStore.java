package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GalleryItemStore {

    private List<GalleryItemView> galleryItems;

    public GalleryItemStore() {
        this.galleryItems = new ArrayList<>();
    }

    public void addNewItem(GalleryItemView item) {
        this.galleryItems.add(item);
    }
}