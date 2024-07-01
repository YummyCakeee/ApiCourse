package ru.nikita.apicourse.api.response.user;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.JsonRestSerializer;

import java.lang.reflect.Type;

@Service
public class UserRestEntitySerializer implements JsonRestSerializer<UserRestEntity> {
    @Override
    public Class<UserRestEntity> entityClass() {
        return UserRestEntity.class;
    }

    @Override
    public JsonElement serialize(UserRestEntity userRestEntity, Type type, JsonSerializationContext jsonSerializationContext) {
        final User user = userRestEntity.getUser();
        final JsonObject response = new JsonObject();
        response.addProperty("id", user.getId());
        response.addProperty("firstName", user.getFirstName());
        response.addProperty("secondName", user.getSecondName());
        response.addProperty("email", user.getEmail());

        return response;
    }
}
