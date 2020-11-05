package com.bringbackdada.site.commands.converters;

import com.bringbackdada.site.commands.BlogCommand;
import com.bringbackdada.site.model.Blog;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BlogCmdToBlog implements Converter<BlogCommand, Blog> {

    private final CreatorCmdToCreator creatorCmdToCreator;

    public BlogCmdToBlog(CreatorCmdToCreator creatorCmdToCreator) {
        this.creatorCmdToCreator = creatorCmdToCreator;
    }

    @Synchronized
    @Nullable
    @Override
    public Blog convert(BlogCommand blogCommand) {

        if (blogCommand == null) { return null; }

        Blog blog = new Blog();
        blog.setId(blogCommand.getId());
        blog.setMilliseconds(blogCommand.getMilliseconds());
        blog.setEntryName(blogCommand.getEntryName());
        blog.setEntryContent(blogCommand.getEntryContent());
        blog.setContentSnippet(blogCommand.getContentSnippet());
        blog.setCreator(creatorCmdToCreator.convert(blogCommand.getCreator()));
        blog.setCategory(blogCommand.getCategory());
        blog.setContentId(blogCommand.getContentId());
        blog.setVisible(blogCommand.getVisible());

        return blog;
    }
}
