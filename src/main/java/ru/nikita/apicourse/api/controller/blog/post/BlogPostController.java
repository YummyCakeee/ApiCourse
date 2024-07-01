package ru.nikita.apicourse.api.controller.blog.post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.nikita.apicourse.api.constants.Endpoints;
import ru.nikita.apicourse.api.response.blog.post.BlogPostRestEntity;
import ru.nikita.apicourse.api.response.blog.post.BlogPostRestEntityPayload;
import ru.nikita.apicourse.core.domain.blog.post.BlogPost;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.service.blog.BlogService;
import ru.nikita.apicourse.dto.BlogPostDto;
import ru.nikita.apicourse.mapper.BlogPostMapper;
import ru.nikita.apicourse.spring.boot.restapi.controller.BaseRestController;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Endpoints.BLOGS)
@RequiredArgsConstructor
public class BlogPostController extends BaseRestController {
    private final BlogService blogService;


    @GetMapping(Endpoints.BLOG_POSTS)
    public Response getPostsByBlogId(@PathVariable("id") Long id) {
        List<BlogPostRestEntity> blogs = blogService.getPostsByBlogId(id).stream()
                .map(BlogPostRestEntityPayload::new).map(entityCreator::create).toList();
        return responseMaker.success().data(new ArrayList<>(blogs)).build();
    }

    @PostMapping(Endpoints.BLOG_POSTS)
    public Response createBlogPost(@AuthenticationPrincipal User user,
                                   @PathVariable("id") Long id,
                                   @RequestBody BlogPostDto blogPostDto) {
        BlogPost blogPost = blogService.createBlogPost(BlogPostMapper.blogPostDtoToBlogPost(blogPostDto),
                id, user.getId());
        return responseMaker.success().data(entityCreator.create(new BlogPostRestEntityPayload(blogPost))).build();
    }

    @PatchMapping(Endpoints.BLOG_POST)
    public Response updateBlogPost(@AuthenticationPrincipal User user,
                                   @PathVariable("id") Long id,
                                   @RequestBody BlogPostDto blogPostDto) {
        BlogPost blogPost = blogService.updateBlogPost(BlogPostMapper.blogPostDtoToBlogPost(blogPostDto), id, user);
        return responseMaker.success().data(entityCreator.create(new BlogPostRestEntityPayload(blogPost))).build();
    }

    @DeleteMapping(Endpoints.BLOG_POST)
    public Response deleteBlogPost(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        blogService.deleteBlogPostById(id, user);
        return responseMaker.success().build();

    }

}
