package ru.nikita.apicourse.core.service.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;

@Getter
@RequiredArgsConstructor
public class RefreshTokenRequest {
    private final AuthStrategyType strategyType;
    private final String token;
}
