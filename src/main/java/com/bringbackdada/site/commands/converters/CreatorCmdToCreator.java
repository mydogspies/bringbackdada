package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.model.Creator;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CreatorCmdToCreator implements Converter<CreatorCommand, Creator> {

    @Synchronized
    @Nullable
    @Override
    public Creator convert(CreatorCommand creatorCommand) {
        if (creatorCommand == null) { return null; }

        Creator creator = new Creator();
        creator.setId(creatorCommand.getId());
        creator.setName(creatorCommand.getName());
        creator.setDescription(creatorCommand.getDescription());
        return creator;
    }
}
