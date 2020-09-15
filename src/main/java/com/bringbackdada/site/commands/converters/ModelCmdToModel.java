package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.ModelCommand;
import com.bringbackdada.site.model.Model;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ModelCmdToModel implements Converter<ModelCommand, Model> {

    @Synchronized
    @Nullable
    @Override
    public Model convert(ModelCommand modelCommand) {

        if (modelCommand == null) { return null; }

        Model model = new Model();
        model.setId(modelCommand.getId());
        model.setName(modelCommand.getName());
        model.setDescription(modelCommand.getDescription());

        return model;
    }
}
