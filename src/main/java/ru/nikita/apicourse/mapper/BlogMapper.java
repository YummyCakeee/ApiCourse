package ru.nikita.apicourse.mapper;

import ru.nikita.apicourse.dto.BlogDto;
import ru.nikita.apicourse.dto.UserDto;
import ru.nikita.apicourse.models.Blog;
import ru.nikita.apicourse.models.User;

public class BlogMapper {

    public static BlogDto blogToBlogDto(Blog blog) {
        return BlogDto.builder()
                .id(blog.getId())
                .name(blog.getName())
                .description(blog.getDescription())
                .userId(blog.getUser().getId())
                .build();
    }

    public static Blog blogDtoToBlog(BlogDto blog) {
        return Blog.builder()
                .id(blog.getId())
                .name(blog.getName())
                .description(blog.getDescription())
                .build();
    }
}
