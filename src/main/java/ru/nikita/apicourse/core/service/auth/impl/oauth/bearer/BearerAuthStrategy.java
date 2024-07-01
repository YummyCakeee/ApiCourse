package ru.nikita.apicourse.core.service.auth.impl.oauth.bearer;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.core.domain.auth.UserAuthIdentity;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.exception.AuthorizeException;
import ru.nikita.apicourse.core.exception.RefreshTokenAuthorizeException;
import ru.nikita.apicourse.core.repository.user.authidentity.UserAuthIdentityRepository;
import ru.nikita.apicourse.core.service.auth.AuthStrategy;
import ru.nikita.apicourse.core.service.auth.RefreshTokenRequest;
import ru.nikita.apicourse.core.service.auth.jwt.JWTService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BearerAuthStrategy implements AuthStrategy<BearerAuthRequest> {
    private final UserAuthIdentityRepository userAuthIdentityRepository;
    private final JWTService jwtService;

    @Override
    public Optional<User> findUserBy(String token) throws AuthorizeException {
        DecodedJWT decodedJWT = jwtService.verifyToken(token);
        if (null == decodedJWT)
            throw new AuthorizeException();

        String userId = jwtService.extractUserId(decodedJWT);
        return userAuthIdentityRepository.findByTypeAndIdentity(getStrategyType(), userId)
                .map(UserAuthIdentity::getUser);
    }

    @Override
    public String refresh(RefreshTokenRequest request) throws RefreshTokenAuthorizeException {
        return jwtService.refreshAccessToken(request);
    }

    @Override
    public AuthStrategyType getStrategyType() {
        return AuthStrategyType.BEARER;
    }

    @Override
    public Class<BearerAuthRequest> getRequestType() {
        return BearerAuthRequest.class;
    }
}
