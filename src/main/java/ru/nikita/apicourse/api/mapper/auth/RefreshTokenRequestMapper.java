package ru.nikita.apicourse.api.mapper.auth;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.api.request.body.auth.RefreshTokenRequestBody;
import ru.nikita.apicourse.core.service.auth.RefreshTokenRequest;
import ru.nikita.apicourse.spring.boot.mapper.mappers.ObjectMapper;

@Service
public class RefreshTokenRequestMapper implements ObjectMapper<RefreshTokenRequestBody, RefreshTokenRequest> {
    @Override
    public RefreshTokenRequest map(RefreshTokenRequestBody source) {
        return new RefreshTokenRequest(source.getStrategy(), source.getRefreshToken());
    }

    @Override
    public Class<RefreshTokenRequestBody> getTargetClass() {
        return RefreshTokenRequestBody.class;
    }

    @Override
    public Class<RefreshTokenRequest> getResultClass() {
        return RefreshTokenRequest.class;
    }
}
