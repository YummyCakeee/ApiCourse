package ru.nikita.apicourse.api.response.blog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.payload.Payload;

@Getter
@RequiredArgsConstructor
public class BlogRestEntityPayload implements Payload<BlogRestEntity> {

    private final Blog blog;
    @Override
    public Class<BlogRestEntity> entityClass() {
        return BlogRestEntity.class;
    }
}
