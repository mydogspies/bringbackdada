package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.model.Content;
import com.bringbackdada.site.model.Creator;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ContentCmdToContent implements Converter<ContentCommand, Content> {

    private final CreatorCmdToCreator creatorConverter;

    public ContentCmdToContent(CreatorCmdToCreator creatorConverter) {
        this.creatorConverter = creatorConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Content convert(ContentCommand contentCommand) {

        if (contentCommand == null) { return null; };

        final Content content = new Content();
        content.setId(contentCommand.getId());
        content.setContentTitle(contentCommand.getContentTitle());
        content.setCategory(contentCommand.getCategory());
        content.setImageFile(contentCommand.getImageFile());
        content.setLicense(contentCommand.getLicense());
        content.setDescription(contentCommand.getDescription());
        content.setOnFrontPage(contentCommand.getOnFrontPage());
        content.setContentOrder(contentCommand.getContentOrder());
        content.setVisible(contentCommand.getVisible());
        content.setAltText(contentCommand.getAltText());

        Set<Creator> creatorSet = new HashSet<>();
        for (CreatorCommand cmd : contentCommand.getCreators()) {
            creatorSet.add(creatorConverter.convert(cmd));
        }
        content.setCreator(creatorSet);

        return content;
    }
}
