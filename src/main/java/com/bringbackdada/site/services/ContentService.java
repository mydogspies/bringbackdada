package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.model.Content;

import java.util.List;

public interface ContentService extends CrudService<Content, Long> {

    ContentCommand saveContentCommand(ContentCommand command);
    List<Content> sortContentByContentOrder(List<Content> sortedList);
    List<ContentCommand> findAllAsCommands();
}
