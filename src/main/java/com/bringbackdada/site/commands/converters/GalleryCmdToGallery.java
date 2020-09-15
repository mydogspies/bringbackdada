package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.model.Gallery;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GalleryCmdToGallery implements Converter<GalleryCommand, Gallery> {

    private final ContentCmdToContent contentConverter;

    public GalleryCmdToGallery(ContentCmdToContent contentConverter) {
        this.contentConverter = contentConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Gallery convert(GalleryCommand galleryCommand) {

        if (galleryCommand == null) { return null; }

        Gallery gallery = new Gallery();
        gallery.setId(galleryCommand.getId());
        gallery.setGalleryTitle(galleryCommand.getGalleryTitle());
        gallery.setDescription(galleryCommand.getDescription());
        gallery.setFeatured(galleryCommand.getIsFeatured());

        List<Content> contentList = new ArrayList<>();
        for (ContentCommand content : galleryCommand.getContent()) {
            contentList.add(contentConverter.convert(content));
        }
        gallery.setContent(contentList);

        return gallery;
    }
}
