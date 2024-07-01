package ru.nikita.apicourse.core.service.auth;

import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.exception.AuthorizeException;
import ru.nikita.apicourse.core.exception.RefreshTokenAuthorizeException;

import java.util.Optional;

public interface AuthStrategy<T extends AuthRequest>{

    Optional<User> findUserBy(String token) throws AuthorizeException;

    String refresh(RefreshTokenRequest request) throws RefreshTokenAuthorizeException;
    AuthStrategyType getStrategyType();
    Class<T> getRequestType();
}
