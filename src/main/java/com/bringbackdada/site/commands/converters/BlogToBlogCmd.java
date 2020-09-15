package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.BlogCommand;
import com.bringbackdada.site.model.Blog;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BlogToBlogCmd implements Converter<Blog, BlogCommand> {

    private final CreatorToCreatorCmd creatorToCreatorCmd;

    public BlogToBlogCmd(CreatorToCreatorCmd creatorToCreatorCmd) {
        this.creatorToCreatorCmd = creatorToCreatorCmd;
    }

    @Synchronized
    @Nullable
    @Override
    public BlogCommand convert(Blog blog) {

        if (blog == null) { return null; }

        final BlogCommand command = new BlogCommand();
        command.setId(blog.getId());
        command.setMilliseconds(blog.getMilliseconds());
        command.setEntryName(blog.getEntryName());
        command.setEntryContent(blog.getEntryContent());
        command.setContentSnippet(blog.getContentSnippet());
        command.setCategory(blog.getCategory());
        command.setCreator(creatorToCreatorCmd.convert(blog.getCreator()));
        command.setContentId(blog.getContentId());

        return command;
    }
}
