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
        command.setImageFile(content.getImageFile());
        command.setLicense(content.getLicense());
        command.setDescription(content.getDescription());
        command.setOnFrontPage(content.getOnFrontPage());

        Set<CreatorCommand> creatorSet = new HashSet<>();
        if(content.getCreator() != null && content.getCreator().size() > 0) {
            for (Creator creator : content.getCreator()) {
                creatorSet.add(creatorConverter.convert(creator));
            }
        }
        command.setCreators(creatorSet);

        return command;
    }
}
