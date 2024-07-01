package ru.nikita.apicourse.api.response.blog.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.core.domain.blog.post.BlogPost;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.payload.Payload;

@Getter
@RequiredArgsConstructor
public class BlogPostRestEntityPayload implements Payload<BlogPostRestEntity> {
    private final BlogPost blogPost;
    @Override
    public Class<BlogPostRestEntity> entityClass() {
        return BlogPostRestEntity.class;
    }
}
