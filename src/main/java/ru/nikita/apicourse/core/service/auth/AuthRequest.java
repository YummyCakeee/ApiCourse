package ru.nikita.apicourse.core.service.auth;

import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;

public interface AuthRequest {
    AuthStrategyType getStrategyType();
}
