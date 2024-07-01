package ru.nikita.apicourse.api.response.auth.oauth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.api.response.user.UserRestEntityPayload;
import ru.nikita.apicourse.core.service.auth.impl.oauth.bearer.response.BearerOAuthTokenResponse;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.JsonRestSerializer;

import java.lang.reflect.Type;
import java.util.Optional;

@Service
public class BearerOAuthTokenRestEntitySerializer implements JsonRestSerializer<BearerOAuthTokenRestEntity> {
    @Override
    public Class<BearerOAuthTokenRestEntity> entityClass() {
        return BearerOAuthTokenRestEntity.class;
    }

    @Override
    public JsonElement serialize(BearerOAuthTokenRestEntity src, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject response = new JsonObject();

        final BearerOAuthTokenResponse tokenResponse = src.getBearerOAuthTokenResponse();
        final RestEntityCreator entityCreator = src.getEntityCreator();
        final GsonSerializer gsonSerializer = src.getGsonSerializer();

        response.addProperty("accessToken", tokenResponse.getToken());
        response.addProperty("refreshToken", tokenResponse.getRefreshToken());
        Optional.ofNullable(tokenResponse.getUser()).map(UserRestEntityPayload::new)
                .map(entityCreator::create).map(gsonSerializer::serialize)
                .ifPresent(userJson -> response.add("user", userJson));
        return response;
    }
}
