package ru.nikita.apicourse.core.service.blog;

import org.springframework.lang.NonNull;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.core.domain.blog.post.BlogPost;
import ru.nikita.apicourse.core.domain.user.User;

import java.util.List;

public interface BlogService {
    Blog createBlog(Blog blog, Long userId);
    List<Blog> getAllBlogs();
    Blog getBlogById(Long id);
    List<Blog> getBlogsByUserId(Long userId);
    Blog updateBlog(Blog blog, Long blogId, @NonNull User user);
    boolean deleteBlogById(Long blogId, @NonNull User user);
    List<BlogPost> getPostsByBlogId(Long blogId);
    BlogPost createBlogPost(BlogPost blogPost, Long blogId, Long userId);
    BlogPost updateBlogPost(BlogPost blogPost, Long blogPostId, @NonNull User user);
    Boolean deleteBlogPostById(Long blogPostId, @NonNull User user);
}
