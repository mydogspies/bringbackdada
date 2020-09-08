package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.ContentCommand;
import com.bringbackdada.site.model.Content;

public interface ContentService extends CrudService<Content, Long> {

    ContentCommand saveContentCommand(ContentCommand command);

}
