package ru.nikita.apicourse.api.mapper.auth;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.api.response.auth.AuthResponsePayload;
import ru.nikita.apicourse.api.response.auth.oauth.BearerOAuthTokenPayload;
import ru.nikita.apicourse.core.service.auth.impl.oauth.bearer.response.BearerOAuthTokenResponse;
import ru.nikita.apicourse.spring.boot.mapper.mappers.ObjectMapper;

@Service
public class BearerAuthTokenResponseMapper  implements ObjectMapper<BearerOAuthTokenResponse, AuthResponsePayload> {
    @Override
    public BearerOAuthTokenPayload map(BearerOAuthTokenResponse source) {
        return new BearerOAuthTokenPayload(source);
    }

    @Override
    public Class<BearerOAuthTokenResponse> getTargetClass() {
        return BearerOAuthTokenResponse.class;
    }

    @Override
    public Class<AuthResponsePayload> getResultClass() {
        return AuthResponsePayload.class;
    }
}
