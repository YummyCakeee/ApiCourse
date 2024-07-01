package ru.nikita.apicourse.api.response.blog;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.Creator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

@Service
public class BlogRestEntityCreator implements Creator<BlogRestEntity, BlogRestEntityPayload> {
    @Override
    public BlogRestEntity create(BlogRestEntityPayload payload, RestEntityCreator restEntityCreator, GsonSerializer gsonSerializer) {
        return new BlogRestEntity(payload.getBlog(), restEntityCreator, gsonSerializer);
    }

    @Override
    public Class<BlogRestEntity> entityType() {
        return BlogRestEntity.class;
    }
}
