package ru.nikita.apicourse.mapper;

import ru.nikita.apicourse.dto.BlogPostDto;
import ru.nikita.apicourse.models.BlogPost;

public class BlogPostMapper {

    public static BlogPostDto blogPostToBlogPostDto(BlogPost blogPost) {
        return BlogPostDto.builder()
                .id(blogPost.getId())
                .blogId(blogPost.getBlog().getId())
                .content(blogPost.getContent())
                .createDate(blogPost.getCreateDate())
                .editDate(blogPost.getEditDate())
                .build();
    }

    public static BlogPost blogPostDtoToBlogPost(BlogPostDto blogPostDto) {
        return BlogPost.builder()
                .id(blogPostDto.getId())
                .content(blogPostDto.getContent())
                .build();
    }
}
