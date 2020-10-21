package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.BlogCommand;
import com.bringbackdada.site.commands.converters.BlogCmdToBlog;
import com.bringbackdada.site.commands.converters.BlogToBlogCmd;
import com.bringbackdada.site.model.Blog;
import com.bringbackdada.site.repositories.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BlogServiceImpl implements BlogService {

    private final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    private final BlogRepository blogRepository;
    private final BlogToBlogCmd blogToBlogCmd;
    private final BlogCmdToBlog blogCmdToBlog;

    public BlogServiceImpl(BlogRepository blogRepository, BlogToBlogCmd blogToBlogCmd, BlogCmdToBlog blogCmdToBlog) {
        this.blogRepository = blogRepository;
        this.blogToBlogCmd = blogToBlogCmd;
        this.blogCmdToBlog = blogCmdToBlog;
    }

    @Override
    public BlogCommand saveBlogCommand(BlogCommand command) {
        Blog detachedBlog = blogCmdToBlog.convert(command);
        Blog savedBlog = blogRepository.save(detachedBlog);
        logger.debug("Blog detached from cmd object and saved as id: " + savedBlog.getId());
        return blogToBlogCmd.convert(savedBlog);
    }

    @Override
    public List<Blog> findAll() {
        Iterable<Blog> result = blogRepository.findAll();
        List<Blog> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        return resultSet;
    }

    @Override
    public Blog findById(Long aLong) {
        Optional<Blog> blog = blogRepository.findById(aLong);

        if(blog.isEmpty()) {
            throw new RuntimeException("Blog not found");
        }

        return blog.get();
    }

    @Override
    public Blog save(Blog object) {
        blogRepository.save(object);
        return object;
    }

    @Override
    public void delete(Blog object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public int count(List<Blog> set) {
        return 0;
    }


}
