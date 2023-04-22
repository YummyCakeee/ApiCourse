package ru.nikita.apicourse.services;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.exceptions.AuthorizeException;
import ru.nikita.apicourse.models.Blog;
import ru.nikita.apicourse.models.Role;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.repositories.BlogRepository;
import ru.nikita.apicourse.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public Blog createBlog(Blog blog, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(""));
        blog.setUser(user);
        blog.setId(null);
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsByUserId(Long userId) {
        return blogRepository.findAllByUserId(userId);
    }

    public boolean deleteBlogById(Long blogId, @NonNull User user) {
        Long blogUserId = blogRepository.findById(blogId).map(Blog::getUser).map(User::getId).orElse(null);
        if (user.getRole().equals(Role.ROLE_ADMIN) || Objects.equals(blogUserId, user.getId()))
            blogRepository.deleteById(blogId);
        else throw new AuthorizeException("You have no permission for this operation");
        return blogRepository.findById(blogId).isEmpty();
    }
}
