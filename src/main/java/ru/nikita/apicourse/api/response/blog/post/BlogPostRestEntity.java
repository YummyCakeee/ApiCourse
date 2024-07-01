package ru.nikita.apicourse.api.response.blog.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.core.domain.blog.post.BlogPost;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.RestEntity;

@Getter
@RequiredArgsConstructor
public class BlogPostRestEntity implements RestEntity<BlogPostRestEntity> {
    private final BlogPost blogPost;
    private final RestEntityCreator restEntityCreator;
    private final GsonSerializer gsonSerializer;

    @Override
    public Class<BlogPostRestEntity> entityClass() {
        return BlogPostRestEntity.class;
    }
}
