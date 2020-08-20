package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Long> {
}
