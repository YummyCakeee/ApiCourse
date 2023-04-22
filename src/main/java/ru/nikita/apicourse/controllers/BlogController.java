package ru.nikita.apicourse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.nikita.apicourse.dto.BlogDto;
import ru.nikita.apicourse.exceptions.AuthorizeException;
import ru.nikita.apicourse.http.response.BasicResponse;
import ru.nikita.apicourse.http.response.DataCollectionResponse;
import ru.nikita.apicourse.http.response.DataResponse;
import ru.nikita.apicourse.mapper.BlogMapper;
import ru.nikita.apicourse.models.Blog;
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
            response.setMessage("Could not create new blog");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<DataCollectionResponse> getBlogs(@RequestParam(value = "user_id", required = false) Long userId) {
        List<BlogDto> blogs;
        if (Objects.isNull(userId))
            blogs = blogService.getAllBlogs().stream().map(BlogMapper::blogToBlogDto).toList();
        else
            blogs = blogService.getBlogsByUserId(userId).stream().map(BlogMapper::blogToBlogDto).toList();
        DataCollectionResponse response = new DataCollectionResponse(blogs);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
}
