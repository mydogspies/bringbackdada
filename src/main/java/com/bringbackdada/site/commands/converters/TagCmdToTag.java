package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.TagCommand;
import com.bringbackdada.site.model.Tag;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TagCmdToTag implements Converter<TagCommand, Tag> {

    @Synchronized
    @Nullable
    @Override
    public Tag convert(TagCommand tagCommand) {

        if (tagCommand == null) { return null; }

        Tag tag = new Tag();
        tag.setId(tagCommand.getId());
        tag.setTag(tagCommand.getTag());

        return tag;
    }
}
