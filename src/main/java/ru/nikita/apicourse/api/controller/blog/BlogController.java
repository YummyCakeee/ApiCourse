package ru.nikita.apicourse.api.controller.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.nikita.apicourse.api.authentication.AuthUser;
import ru.nikita.apicourse.api.constants.Endpoints;
import ru.nikita.apicourse.api.response.blog.BlogRestEntity;
import ru.nikita.apicourse.api.response.blog.BlogRestEntityPayload;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.service.blog.BlogService;
import ru.nikita.apicourse.dto.BlogDto;
import ru.nikita.apicourse.mapper.BlogMapper;
import ru.nikita.apicourse.spring.boot.restapi.controller.BaseRestController;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Endpoints.BLOGS)
@RequiredArgsConstructor
public class BlogController extends BaseRestController {
    private final BlogService blogService;

    @PostMapping
    public Response createBlog(@AuthenticationPrincipal User user, @RequestBody BlogDto blogDto) {
        Blog newBlog = blogService.createBlog(BlogMapper.blogDtoToBlog(blogDto), user.getId());
        return responseMaker.success()
                .httpStatus(HttpStatus.CREATED)
                .data(entityCreator.create(new BlogRestEntityPayload(newBlog)))
                .build();
    }

    @GetMapping
    public Response getBlogs(@AuthUser User user) {
        List<BlogRestEntity> blogs = blogService.getAllBlogs().stream().map(BlogRestEntityPayload::new).map(entityCreator::create).toList();
        return responseMaker.success().data(new ArrayList<>(blogs)).build();
    }

    @GetMapping(Endpoints.TARGET_ID)
    public Response getBlog(@PathVariable("id") Long id) {
        Blog blog = blogService.getBlogById(id);
        return responseMaker.success().data(entityCreator.create(new BlogRestEntityPayload(blog))).build();
    }

    @PatchMapping(Endpoints.TARGET_ID)
    public Response updateBlog(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long id,
                               @RequestBody BlogDto blogDto) {
        Blog blog = blogService.updateBlog(BlogMapper.blogDtoToBlog(blogDto), id, user);
        return responseMaker.success().data(entityCreator.create(new BlogRestEntityPayload(blog))).build();
    }

    @DeleteMapping(Endpoints.TARGET_ID)
    public Response deleteBlog(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        blogService.deleteBlogById(id, user);
        return responseMaker.success().build();
    }


}
