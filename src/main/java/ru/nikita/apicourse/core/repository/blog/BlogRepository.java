package ru.nikita.apicourse.core.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.spring.boot.data.repository.CommonRepository;

import java.util.List;

@Repository
public interface BlogRepository extends CommonRepository<Blog> {
    List<Blog> findAllByUserId(Long userId);
}
