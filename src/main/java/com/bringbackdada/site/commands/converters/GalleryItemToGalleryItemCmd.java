package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.GalleryItemCommand;
import org.springframework.core.convert.converter.Converter;
import com.bringbackdada.site.model.GalleryItem;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GalleryItemToGalleryItemCmd implements Converter<GalleryItem, GalleryItemCommand>{

    private final ContentToContentCmd contentToContentCmd;

    public GalleryItemToGalleryItemCmd(ContentToContentCmd contentToContentCmd) {
        this.contentToContentCmd = contentToContentCmd;
    }

    @Synchronized
    @Nullable
    @Override
    public GalleryItemCommand convert(GalleryItem galleryItem) {

        final GalleryItemCommand command = new GalleryItemCommand();
        command.setId(galleryItem.getId());
        command.setItemOrder(galleryItem.getItemOrder());
        command.setVisible(galleryItem.getVisible());
        command.setContent(contentToContentCmd.convert(galleryItem.getContent()));
        return command;
    }
}
