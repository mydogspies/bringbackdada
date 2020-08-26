package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Blog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BlogRepositoryIntegrationTest {

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void findById() {

        Optional<Blog> blog = blogRepository.findById(1L);

    }
}