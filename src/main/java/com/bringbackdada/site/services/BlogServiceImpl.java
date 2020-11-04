package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.BlogCommand;
import com.bringbackdada.site.commands.converters.BlogCmdToBlog;
import com.bringbackdada.site.commands.converters.BlogToBlogCmd;
import com.bringbackdada.site.model.Blog;
import com.bringbackdada.site.repositories.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<BlogCommand> findAllAsCommands() {
        List<BlogCommand> blogCmdList = new ArrayList<>();
        Iterable<Blog> result = blogRepository.findAll();
        List<Blog> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        for (Blog blog : resultSet) {
            blogCmdList.add(blogToBlogCmd.convert(blog));
        }
        return blogCmdList;
    }

    @Override
    public Blog findById(Long aLong) {

        Optional<Blog> blog = blogRepository.findById(aLong);
        if(blog.isEmpty()) {
            logger.error("findById(): No such blog found with id " + aLong);
            throw new RuntimeException("Blog not found");
        }
        return blog.get();
    }

    @Override
    public Blog save(Blog object) {
        blogRepository.save(object);
        logger.debug("Blog [" + object.getEntryName() + "] has been saved");
        return object;
    }

    @Override
    public void delete(Blog object) {
        logger.debug("Blog [" + object.getEntryName() + "] with id " + object.getId() + " has been permanently deleted");
        blogRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        blogRepository.deleteById(aLong);
        logger.debug("Blog with id " + aLong + " has been permanently deleted");
    }

    @Override
    public int count(List<Blog> set) {
        return 0;
    }


}
