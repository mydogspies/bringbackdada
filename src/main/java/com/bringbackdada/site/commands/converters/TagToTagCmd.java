package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.TagCommand;
import com.bringbackdada.site.model.Tag;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TagToTagCmd implements Converter<Tag, TagCommand> {

    @Override
    public TagCommand convert(Tag tag) {
        return null;
    }
}
