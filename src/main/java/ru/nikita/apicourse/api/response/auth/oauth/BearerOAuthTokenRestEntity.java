package ru.nikita.apicourse.api.response.auth.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.api.response.auth.AuthResponseRestEntity;
import ru.nikita.apicourse.core.service.auth.impl.oauth.bearer.response.BearerOAuthTokenResponse;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

@Getter
@RequiredArgsConstructor
public class BearerOAuthTokenRestEntity implements AuthResponseRestEntity<BearerOAuthTokenRestEntity> {
    private final BearerOAuthTokenResponse bearerOAuthTokenResponse;

    private final RestEntityCreator entityCreator;
    private final GsonSerializer gsonSerializer;
    @Override
    public Class<BearerOAuthTokenRestEntity> entityClass() {
        return BearerOAuthTokenRestEntity.class;
    }
}
