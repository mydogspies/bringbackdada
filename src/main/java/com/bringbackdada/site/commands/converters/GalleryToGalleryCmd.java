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
public class GalleryToGalleryCmd implements Converter<Gallery, GalleryCommand> {

    private final ContentToContentCmd contentConverter;

    public GalleryToGalleryCmd(ContentToContentCmd contentConverter) {
        this.contentConverter = contentConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public GalleryCommand convert(Gallery gallery) {

        if (gallery == null) { return null; }

        GalleryCommand command = new GalleryCommand();
        command.setId(gallery.getId());
        command.setGalleryTitle(gallery.getGalleryTitle());
        command.setDescription(gallery.getDescription());
        command.setIsFeatured(gallery.getFeatured());

        List<ContentCommand> contentList = new ArrayList<>();
        for (Content content : gallery.getContent()) {
            contentList.add(contentConverter.convert(content));
        }
        command.setContent(contentList);

        return command;
    }
}
