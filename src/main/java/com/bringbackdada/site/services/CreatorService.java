package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.CreatorCommand;
import com.bringbackdada.site.model.Creator;

import java.util.List;

public interface CreatorService extends CrudService<Creator, Long> {
    List<CreatorCommand> findAllAsCommands();
}
