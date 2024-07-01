package ru.nikita.apicourse.api.response.blog;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.JsonRestSerializer;

import java.lang.reflect.Type;

@Service
public class BlogRestEntitySerializer implements JsonRestSerializer<BlogRestEntity> {
    @Override
    public Class<BlogRestEntity> entityClass() {
        return BlogRestEntity.class;
    }

    @Override
    public JsonElement serialize(BlogRestEntity blogRestEntity, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject response = new JsonObject();

        final Blog blog = blogRestEntity.getBlog();

        response.addProperty("id", blog.getId());
        response.addProperty("name", blog.getName());
        response.addProperty("description", blog.getDescription());

        return response;
    }
}
