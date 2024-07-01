package ru.nikita.apicourse.api.response.blog.post;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.core.domain.blog.post.BlogPost;
import ru.nikita.apicourse.core.utils.DateTimeUtils;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.JsonRestSerializer;

import java.lang.reflect.Type;

@Service
public class BlogPostRestEntitySerializer implements JsonRestSerializer<BlogPostRestEntity> {
    @Override
    public Class<BlogPostRestEntity> entityClass() {
        return BlogPostRestEntity.class;
    }

    @Override
    public JsonElement serialize(BlogPostRestEntity blogPostRestEntity, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject response = new JsonObject();
        final BlogPost blogPost = blogPostRestEntity.getBlogPost();
        response.addProperty("id", blogPost.getId());
        response.addProperty("blogId", blogPost.getBlog().getId());
        response.addProperty("content", blogPost.getContent());
        response.addProperty("createdDate", DateTimeUtils.toUTC(blogPost.getCreatedAt()));
        response.addProperty("editDate", DateTimeUtils.toUTC(blogPost.getUpdatedAt()));

        return response;
    }
}
