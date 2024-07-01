package ru.nikita.apicourse.api.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikita.apicourse.api.constants.Endpoints;
import ru.nikita.apicourse.api.response.blog.BlogRestEntity;
import ru.nikita.apicourse.api.response.blog.BlogRestEntityPayload;
import ru.nikita.apicourse.api.response.user.UserRestEntity;
import ru.nikita.apicourse.api.response.user.UserRestEntityPayload;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.service.blog.BlogService;
import ru.nikita.apicourse.core.service.user.UserService;
import ru.nikita.apicourse.spring.boot.restapi.controller.BaseRestController;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;

import java.util.ArrayList;
import java.util.List;

;

@RestController()
@RequestMapping(Endpoints.USERS)
@RequiredArgsConstructor
public class UserController extends BaseRestController {
    private final UserService userService;
    private final BlogService blogService;

    @GetMapping
    public Response getAll() {
        List<UserRestEntity> users = userService.getAllUsers().stream()
                .map(UserRestEntityPayload::new).map(entityCreator::create).toList();
        return responseMaker.success().data(new ArrayList<>(users)).build();
    }

    @GetMapping(Endpoints.TARGET_ID)
    public Response getUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return responseMaker.success().data(entityCreator.create(new UserRestEntityPayload(user))).build();
    }

    @GetMapping(Endpoints.USER_BLOGS)
    public Response getUserBlogs(@PathVariable("id") Long id) {
        List<BlogRestEntity> blogs = blogService.getBlogsByUserId(id).stream()
                .map(BlogRestEntityPayload::new).map(entityCreator::create).toList();

        return responseMaker.success().data(new ArrayList<>(blogs)).build();
    }
}
