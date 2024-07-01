package ru.nikita.apicourse.api.response.auth.oauth;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.Creator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

@Service
public class BearerOAuthTokenRestEntityCreator implements Creator<BearerOAuthTokenRestEntity, BearerOAuthTokenPayload> {
    @Override
    public BearerOAuthTokenRestEntity create(BearerOAuthTokenPayload payload, RestEntityCreator restEntityCreator, GsonSerializer gsonSerializer) {
        return new BearerOAuthTokenRestEntity(
                payload.getBearerOAuthTokenResponse(),
                restEntityCreator, gsonSerializer
        );
    }

    @Override
    public Class<BearerOAuthTokenRestEntity> entityType() {
        return BearerOAuthTokenRestEntity.class;
    }
}
