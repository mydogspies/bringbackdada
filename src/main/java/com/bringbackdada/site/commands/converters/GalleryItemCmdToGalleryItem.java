package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.GalleryItemCommand;
import com.bringbackdada.site.model.GalleryItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GalleryItemCmdToGalleryItem implements Converter<GalleryItemCommand, GalleryItem> {

    private final ContentCmdToContent contentCmdToContent;

    public GalleryItemCmdToGalleryItem(ContentCmdToContent contentCmdToContent) {
        this.contentCmdToContent = contentCmdToContent;
    }

    @Override
    public GalleryItem convert(GalleryItemCommand galleryItemCommand) {

        GalleryItem gallery = new GalleryItem();
        gallery.setId(galleryItemCommand.getId());
        gallery.setContent(contentCmdToContent.convert(galleryItemCommand.getContent()));
        gallery.setItemOrder(galleryItemCommand.getItemOrder());
        gallery.setVisible(galleryItemCommand.getVisible());
        return gallery;
    }
}
