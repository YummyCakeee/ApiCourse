package ru.nikita.apicourse.core.service.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.service.auth.RefreshTokenRequest;
import ru.nikita.apicourse.core.service.auth.impl.oauth.bearer.response.BearerOAuthTokenResponse;

public interface JWTService {

    String extractUserId(DecodedJWT decodedJWT);
    BearerOAuthTokenResponse generateToken(User user);
    String refreshAccessToken(RefreshTokenRequest request);
    DecodedJWT verifyToken(String token);
}
