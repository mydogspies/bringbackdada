package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.model.Content;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ContentCmdToContent implements Converter<ContentCommand, Content> {

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

        return content;
    }
}
