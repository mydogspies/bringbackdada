package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Blog;
import com.bringbackdada.site.repositories.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Set<Blog> findAll() {
        Iterable<Blog> result = blogRepository.findAll();
        Set<Blog> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toSet());
        return resultSet;
    }

    @Override
    public Blog findById(Long aLong) {
        Optional<Blog> blog = blogRepository.findById(aLong);
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
    public int count(Set<Blog> set) {
        return 0;
    }
}
