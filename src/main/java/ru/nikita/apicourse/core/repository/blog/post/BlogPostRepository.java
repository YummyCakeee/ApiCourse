package ru.nikita.apicourse.core.repository.blog.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikita.apicourse.core.domain.blog.post.BlogPost;
import ru.nikita.apicourse.spring.boot.data.repository.CommonRepository;

import java.util.List;

@Repository
public interface BlogPostRepository extends CommonRepository<BlogPost> {
    List<BlogPost> findAllByBlogId(Long blogId);
}
