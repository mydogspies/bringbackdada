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
public class GalleryToGalleryCmd implements Converter<Gallery, GalleryCommand> {

    private final GalleryItemToGalleryItemCmd converter;

    public GalleryToGalleryCmd(GalleryItemToGalleryItemCmd converter) {
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public GalleryCommand convert(Gallery gallery) {

        GalleryCommand command = new GalleryCommand();
        command.setId(gallery.getId());
        command.setGalleryName(gallery.getGalleryName());
        command.setDescription(gallery.getDescription());
        command.setFrontPageFeatured(gallery.getFrontPageFeatured());
        command.setGalleryOrder(gallery.getGalleryOrder());

        List<GalleryItemCommand> contentList = new ArrayList<>();
        for (GalleryItem galleryItem : gallery.getGalleryItem()) {
            contentList.add(converter.convert(galleryItem));
        }
        command.setGalleryItem(contentList);

        return command;
    }
}
