package ru.nikita.apicourse.services;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.exceptions.AuthorizeException;
import ru.nikita.apicourse.exceptions.InvalidRequestException;
import ru.nikita.apicourse.models.Blog;
import ru.nikita.apicourse.models.BlogPost;
import ru.nikita.apicourse.models.Role;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.repositories.BlogPostRepository;
import ru.nikita.apicourse.repositories.BlogRepository;
import ru.nikita.apicourse.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final BlogPostRepository blogPostRepository;

    public Blog createBlog(Blog blog, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("This user is not exists"));
        blog.setUser(user);
        blog.setId(null);
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public List<Blog> getBlogsByUserId(Long userId) {
        return blogRepository.findAllByUserId(userId);
    }

    public Blog updateBlog(Blog blog, Long blogId, @NonNull User user) {
        Blog foundBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new InvalidRequestException("There is no blog with id " + blogId));
        if (!user.getRole().equals(Role.ROLE_ADMIN) && !foundBlog.getUser().getId().equals(user.getId()))
            throw new AuthorizeException("User with id " + user.getId() + " is not a blog owner");
        blog.setUser(foundBlog.getUser());
        return blogRepository.save(blog);
    }

    public boolean deleteBlogById(Long blogId, @NonNull User user) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new InvalidRequestException("There is no blog with id " + blogId));
        if (!user.getRole().equals(Role.ROLE_ADMIN) && !Objects.equals(blog.getUser().getId(), user.getId()))
            throw new AuthorizeException("You have no permission for this operation");
        blogRepository.deleteById(blogId);
        return blogRepository.findById(blogId).isEmpty();
    }

    public List<BlogPost> getPostsByBlogId(Long blogId) {
        return blogPostRepository.findAllByBlogId(blogId);
    }

    public BlogPost createBlogPost(BlogPost blogPost, Long blogId, Long userId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() ->
                new InvalidRequestException("There is no blog with id " + blogId));
        if (!blog.getUser().getId().equals(userId))
            throw new InvalidRequestException("User with id " + userId + " is not a blog owner");
        blogPost.setId(null);
        blogPost.setBlog(blog);
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updateBlogPost(BlogPost blogPost, Long blogPostId, @NonNull User user) {
        BlogPost foundBlogPost = blogPostRepository.findById(blogPostId)
                .orElseThrow(() -> new InvalidRequestException("There is no blog post with id " + blogPostId));
        if (!user.getRole().equals(Role.ROLE_ADMIN) && !foundBlogPost.getBlog().getUser().getId().equals(user.getId()))
            throw new AuthorizeException("User with id " + user.getId() + " is not a blog post owner");
        blogPost.setId(blogPostId);
        blogPost.setBlog(foundBlogPost.getBlog());
        blogPost.setCreateDate(foundBlogPost.getCreateDate());
        return blogPostRepository.save(blogPost);
    }

    public Boolean deleteBlogPostById(Long blogPostId, @NonNull User user) {
        BlogPost blogPost = blogPostRepository.findById(blogPostId)
                .orElseThrow(() -> new InvalidRequestException("There is no blog post with id " + blogPostId));
        if (!user.getRole().equals(Role.ROLE_ADMIN) && !blogPost.getBlog().getUser().getId().equals(user.getId()))
            throw new AuthorizeException("User with id " + user.getId() + " is not a blog post owner");
        blogPostRepository.deleteById(blogPostId);
        return blogPostRepository.findById(blogPostId).isEmpty();
    }
}
