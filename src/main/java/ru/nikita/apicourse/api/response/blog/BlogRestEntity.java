package ru.nikita.apicourse.api.response.blog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.RestEntity;

@Getter
@RequiredArgsConstructor
public class BlogRestEntity implements RestEntity<BlogRestEntity> {

    private final Blog blog;
    private final RestEntityCreator entityCreator;
    private final GsonSerializer gsonSerializer;

    @Override
    public Class<BlogRestEntity> entityClass() {
        return BlogRestEntity.class;
    }
}
