package ru.nikita.apicourse.core.service.blog.impl;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.core.exception.AuthorizeException;
import ru.nikita.apicourse.core.exception.EntityNotFoundException;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.core.domain.blog.post.BlogPost;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.repository.blog.post.BlogPostRepository;
import ru.nikita.apicourse.core.repository.blog.BlogRepository;
import ru.nikita.apicourse.core.repository.user.UserRepository;
import ru.nikita.apicourse.core.service.blog.BlogService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final BlogPostRepository blogPostRepository;

    @Override
    public Blog createBlog(Blog blog, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("This user is not exists"));
        blog.setUser(user);
        blog.setId(null);
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Override
    public List<Blog> getBlogsByUserId(Long userId) {
        return blogRepository.findAllByUserId(userId);
    }

    @Override
    public Blog updateBlog(Blog blog, Long blogId, @NonNull User user) {
        Blog foundBlog = blogRepository.findById(blogId)
                .orElseThrow(EntityNotFoundException::new);
//        if (!user.getUserRole().equals(UserRole.ROLE_ADMIN) && !foundBlog.getUser().getId().equals(user.getId()))
//            throw new AuthorizeException();
        blog.setId(blogId);
        blog.setUser(foundBlog.getUser());
        return blogRepository.save(blog);
    }

    @Override
    public boolean deleteBlogById(Long blogId, @NonNull User user) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(EntityNotFoundException::new);
//        if (!user.getUserRole().equals(UserRole.ROLE_ADMIN) && !Objects.equals(blog.getUser().getId(), user.getId()))
//            throw new AuthorizeException();
        blogRepository.deleteById(blogId);
        return blogRepository.findById(blogId).isEmpty();
    }

    @Override
    public List<BlogPost> getPostsByBlogId(Long blogId) {
        return blogPostRepository.findAllByBlogId(blogId);
    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost, Long blogId, Long userId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(EntityNotFoundException::new);
        if (!blog.getUser().getId().equals(userId))
            throw new AuthorizeException();
        blogPost.setId(null);
        blogPost.setBlog(blog);
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(BlogPost blogPost, Long blogPostId, @NonNull User user) {
        BlogPost foundBlogPost = blogPostRepository.findById(blogPostId)
                .orElseThrow(EntityNotFoundException::new);
//        if (!user.getUserRole().equals(UserRole.ROLE_ADMIN) && !foundBlogPost.getBlog().getUser().getId().equals(user.getId()))
//            throw new AuthorizeException();
        blogPost.setId(blogPostId);
        blogPost.setBlog(foundBlogPost.getBlog());
        return blogPostRepository.save(blogPost);
    }

    @Override
    public Boolean deleteBlogPostById(Long blogPostId, @NonNull User user) {
        BlogPost blogPost = blogPostRepository.findById(blogPostId)
                .orElseThrow(EntityNotFoundException::new);
//        if (!user.getUserRole().equals(UserRole.ROLE_ADMIN) && !blogPost.getBlog().getUser().getId().equals(user.getId()))
//            throw new AuthorizeException();
        blogPostRepository.deleteById(blogPostId);
        return blogPostRepository.findById(blogPostId).isEmpty();
    }
}
