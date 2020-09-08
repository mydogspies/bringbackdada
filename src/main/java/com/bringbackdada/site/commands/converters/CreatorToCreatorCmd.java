package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.model.Creator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreatorToCreatorCmd implements Converter<Creator, CreatorCommand> {

    @Override
    public CreatorCommand convert(Creator creator) {
        return null;
    }
}
