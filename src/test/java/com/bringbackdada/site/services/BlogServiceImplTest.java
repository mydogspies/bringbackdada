package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Blog;
import com.bringbackdada.site.repositories.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BlogServiceImplTest {

    BlogService blogService;

    @Mock
    BlogRepository blogRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        blogService = new BlogServiceImpl(blogRepository);
    }

    @Test
    void findBlogById() {
        Blog blog = new Blog();
        blog.setId(1L);
        Optional<Blog>  blogOptional = Optional.of(blog);

        when(blogRepository.findById(anyLong())).thenReturn(blogOptional);

        Blog returnedBlog = blogService.findById(1L);

        assertNotNull(returnedBlog);
        verify(blogRepository, times(1)).findById(1L);
    }
}