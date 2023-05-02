package ru.nikita.apicourse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.nikita.apicourse.dto.BlogDto;
import ru.nikita.apicourse.dto.BlogPostDto;
import ru.nikita.apicourse.exceptions.AuthorizeException;
import ru.nikita.apicourse.http.response.BasicResponse;
import ru.nikita.apicourse.http.response.DataCollectionResponse;
import ru.nikita.apicourse.http.response.DataResponse;
import ru.nikita.apicourse.mapper.BlogMapper;
import ru.nikita.apicourse.mapper.BlogPostMapper;
import ru.nikita.apicourse.models.Blog;
import ru.nikita.apicourse.models.BlogPost;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.services.BlogService;

import java.util.List;
import java.util.Objects;

import static ru.nikita.apicourse.constants.Endpoints.API_ROOT;

@RestController
@RequestMapping(API_ROOT + "/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<DataResponse> createBlog(@AuthenticationPrincipal User user, @RequestBody BlogDto blogDto) {
        DataResponse response = new DataResponse();
        try {
            Blog newBlog = blogService.createBlog(BlogMapper.blogDtoToBlog(blogDto), user.getId());
            response.setData(BlogMapper.blogToBlogDto(newBlog));
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage("Could not create new blog: " + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<DataCollectionResponse> getBlogs() {
        List<BlogDto> blogs = blogService.getAllBlogs().stream().map(BlogMapper::blogToBlogDto).toList();
        DataCollectionResponse response = new DataCollectionResponse(blogs);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse> getBlog(@PathVariable("id") Long id) {
        BlogDto blog = BlogMapper.blogToBlogDto(blogService.getBlogById(id));
        DataResponse response = new DataResponse(blog);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{blogId}")
    public ResponseEntity<BasicResponse> updateBlog(@AuthenticationPrincipal User user,
                                                    @PathVariable Long blogId,
                                                    @RequestBody BlogDto blogDto) {
        DataResponse response = new DataResponse();
        try {
            Blog blog =
                    blogService.updateBlog(BlogMapper.blogDtoToBlog(blogDto), blogId, user);
            response.setData(BlogMapper.blogToBlogDto(blog));
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<BasicResponse> deleteBlog(@AuthenticationPrincipal User user, @PathVariable Long blogId) {
        try {
            return new ResponseEntity<>(
                    new BasicResponse(blogService.deleteBlogById(blogId, user)),
                    HttpStatus.OK
            );

        } catch (AuthorizeException ex) {
            return new ResponseEntity<>(new BasicResponse(false, ex.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{blogId}/posts")
    public ResponseEntity<DataCollectionResponse> getPostsByBlogId(@PathVariable Long blogId) {
        List<BlogPostDto> blogs = blogService.getPostsByBlogId(blogId).stream()
                .map(BlogPostMapper::blogPostToBlogPostDto).toList();
        DataCollectionResponse response = new DataCollectionResponse(blogs);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{blogId}/posts")
    public ResponseEntity<DataResponse> createBlogPost(@AuthenticationPrincipal User user,
                                                       @PathVariable Long blogId,
                                                       @RequestBody BlogPostDto blogPostDto) {
        DataResponse response = new DataResponse();
        try {
            BlogPost blogPost = blogService.createBlogPost(BlogPostMapper.blogPostDtoToBlogPost(blogPostDto),
                    blogId, user.getId());
            response.setData(BlogPostMapper.blogPostToBlogPostDto(blogPost));
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage("Could not create new blog post: " + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/posts/{postId}")
    public ResponseEntity<BasicResponse> updateBlogPost(@AuthenticationPrincipal User user,
                                                        @PathVariable Long postId,
                                                        @RequestBody BlogPostDto blogPostDto) {
        DataResponse response = new DataResponse();
        try {
            BlogPost blogPost =
                    blogService.updateBlogPost(BlogPostMapper.blogPostDtoToBlogPost(blogPostDto), postId, user);
            response.setData(BlogPostMapper.blogPostToBlogPostDto(blogPost));
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<BasicResponse> deleteBlogPost(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        try {
            return new ResponseEntity<>(
                    new BasicResponse(blogService.deleteBlogPostById(postId, user)),
                    HttpStatus.OK
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(new BasicResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
}
