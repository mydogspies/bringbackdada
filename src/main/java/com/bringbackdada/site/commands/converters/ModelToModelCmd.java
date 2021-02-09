package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.ModelCommand;
import com.bringbackdada.site.model.Model;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ModelToModelCmd implements Converter<Model, ModelCommand> {

    @Synchronized
    @Nullable
    @Override
    public ModelCommand convert(Model model) {

        if (model == null) { return null; }

        ModelCommand command = new ModelCommand();
        command.setId(model.getId());
        command.setName(model.getName());
        command.setDescription(model.getDescription());

        return command;
    }
}
