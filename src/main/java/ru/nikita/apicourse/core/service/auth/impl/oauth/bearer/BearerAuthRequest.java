package ru.nikita.apicourse.core.service.auth.impl.oauth.bearer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.service.auth.AuthRequest;

@Getter
@RequiredArgsConstructor
public class BearerAuthRequest implements AuthRequest {
    private final String code;


    @Override
    public AuthStrategyType getStrategyType() {
        return AuthStrategyType.BEARER;
    }
}
