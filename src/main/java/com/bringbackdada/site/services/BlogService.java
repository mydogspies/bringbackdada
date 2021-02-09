package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.BlogCommand;
import com.bringbackdada.site.model.Blog;

import java.util.List;

public interface BlogService extends CrudService<Blog, Long> {

    BlogCommand saveBlogCommand(BlogCommand command);
    List<BlogCommand> findAllAsCommands();
}
