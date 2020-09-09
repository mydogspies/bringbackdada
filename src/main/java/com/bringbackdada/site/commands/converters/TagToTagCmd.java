package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.TagCommand;
import com.bringbackdada.site.model.Tag;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TagToTagCmd implements Converter<Tag, TagCommand> {

    @Synchronized
    @Nullable
    @Override
    public TagCommand convert(Tag tag) {
        return null;
    }
}
