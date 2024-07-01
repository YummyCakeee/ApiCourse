package ru.nikita.apicourse.core.service.auth;

import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.exception.AuthorizeException;

public interface AuthenticationService {
    User findById(Long userId) throws AuthorizeException;
    User findUserBy(AuthStrategyType strategyType, String token) throws AuthorizeException;

    String refresh(RefreshTokenRequest request);
}
