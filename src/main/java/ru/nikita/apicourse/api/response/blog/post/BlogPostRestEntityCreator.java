package ru.nikita.apicourse.api.response.blog.post;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.Creator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

@Service
public class BlogPostRestEntityCreator implements Creator<BlogPostRestEntity, BlogPostRestEntityPayload> {
    @Override
    public BlogPostRestEntity create(BlogPostRestEntityPayload payload, RestEntityCreator restEntityCreator, GsonSerializer gsonSerializer) {
        return new BlogPostRestEntity(payload.getBlogPost(), restEntityCreator, gsonSerializer);
    }

    @Override
    public Class<BlogPostRestEntity> entityType() {
        return BlogPostRestEntity.class;
    }
}
