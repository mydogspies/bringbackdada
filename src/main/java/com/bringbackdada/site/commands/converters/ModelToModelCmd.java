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
        return null;
    }
}
