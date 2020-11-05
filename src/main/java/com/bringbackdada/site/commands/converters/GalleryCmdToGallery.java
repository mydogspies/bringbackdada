package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.commands.GalleryItemCommand;
import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.model.GalleryItem;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GalleryCmdToGallery implements Converter<GalleryCommand, Gallery> {

    private final GalleryItemCmdToGalleryItem converter;

    public GalleryCmdToGallery(GalleryItemCmdToGalleryItem converter) {
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public Gallery convert(GalleryCommand galleryCommand) {

        Gallery gallery = new Gallery();
        gallery.setId(galleryCommand.getId());
        gallery.setGalleryName(galleryCommand.getGalleryName());
        gallery.setDescription(galleryCommand.getDescription());
        gallery.setFrontPageFeatured(galleryCommand.getFrontPageFeatured());
        gallery.setGalleryOrder(galleryCommand.getGalleryOrder());
        gallery.setVisible(galleryCommand.getVisible());

        List<GalleryItem> contentList = new ArrayList<>();
        for (GalleryItemCommand galleryItemCmd : galleryCommand.getGalleryItem()) {
            contentList.add(converter.convert(galleryItemCmd));
        }
        gallery.setGalleryItem(contentList);

        return gallery;
    }
}
