package ru.nikita.apicourse.api.response.auth.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.api.response.auth.AuthResponsePayload;
import ru.nikita.apicourse.core.service.auth.impl.oauth.bearer.response.BearerOAuthTokenResponse;

@Getter
@RequiredArgsConstructor
public class BearerOAuthTokenPayload implements AuthResponsePayload<BearerOAuthTokenRestEntity> {

    private final BearerOAuthTokenResponse bearerOAuthTokenResponse;

    @Override
    public Class<BearerOAuthTokenRestEntity> entityClass() {
        return BearerOAuthTokenRestEntity.class;
    }
}
