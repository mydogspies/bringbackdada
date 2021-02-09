package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.model.Creator;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CreatorToCreatorCmd implements Converter<Creator, CreatorCommand> {

    @Synchronized
    @Nullable
    @Override
    public CreatorCommand convert(Creator creator) {

        if (creator == null) { return null; }

        final CreatorCommand command = new CreatorCommand();
        command.setId(creator.getId());
        command.setName(creator.getName());
        command.setDescription(creator.getDescription());

        return command;
    }
}
