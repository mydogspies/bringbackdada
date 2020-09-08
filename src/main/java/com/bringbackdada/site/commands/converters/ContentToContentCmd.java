package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.model.Content;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ContentToContentCmd implements Converter<Content, ContentCommand> {

    private final CreatorToCreatorCmd creatorConverter;
    private final ModelToModelCmd modelConverter;
    private final TagToTagCmd tagConverter;

    public ContentToContentCmd(CreatorToCreatorCmd creatorConverter, ModelToModelCmd modelConverter,
                               TagToTagCmd tagConverter) {
        this.creatorConverter = creatorConverter;
        this.modelConverter = modelConverter;
        this.tagConverter = tagConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public ContentCommand convert(Content content) {

        if (content == null) { return null; }

        final ContentCommand command = new ContentCommand();
        command.setId(content.getId());
        command.setContentTitle(content.getContentTitle());
        command.setCategory(content.getCategory());

        // TODO image implementation

        command.setLicense(content.getLicense());
        command.setDescription(content.getDescription());

        command.setOnFrontPage(content.getOnFrontPage());

        return command;
    }
}
