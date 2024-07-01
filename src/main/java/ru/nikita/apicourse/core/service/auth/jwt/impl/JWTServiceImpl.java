package ru.nikita.apicourse.core.service.auth.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.refreshtoken.RefreshToken;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.exception.AuthorizeException;
import ru.nikita.apicourse.core.exception.RefreshTokenAuthorizeException;
import ru.nikita.apicourse.core.repository.refreshtoken.RefreshTokenRepository;
import ru.nikita.apicourse.core.service.auth.RefreshTokenRequest;
import ru.nikita.apicourse.core.service.auth.impl.oauth.bearer.response.BearerOAuthTokenResponse;
import ru.nikita.apicourse.core.service.auth.jwt.JWTService;
import ru.nikita.apicourse.core.utils.RandomUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    @Value("${jwt.access.token}")
    private String accessTokenSecret;
    @Value("${jwt.access.lifetime}")
    private Long accessTokenLifetime;
    @Value("${jwt.refresh.lifetime}")
    private Long refreshTokenLifetime;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String extractUserId(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("id").asString();
    }

    @Override
    public BearerOAuthTokenResponse generateToken(User user) {
        final String accessToken = generateAccessToken(user);

        final RefreshToken refreshToken = generateRefreshToken(user);

        return new BearerOAuthTokenResponse(accessToken, refreshToken.getToken(), user.getId().toString(), user.getFirstName(), user.getSecondName(), user.getEmail());
    }

    @Override
    @Transactional
    public String refreshAccessToken(RefreshTokenRequest request) {

        final RefreshToken refreshToken = refreshTokenRepository.findByTokenAndType(request.getToken(), request.getStrategyType())
                .orElseThrow(RefreshTokenAuthorizeException::new);

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenAuthorizeException();
        }

        return generateAccessToken(refreshToken.getUser());
    }

    @Override
    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(accessTokenSecret))
                .withSubject("User info")
                .withIssuer("social")
                .withClaimPresence("id")
                .build();
        try {
            return verifier.verify(token);
        } catch (Exception ex) {
            return null;
        }
    }

    private String generateAccessToken(User user) {
        return JWT.create()
                .withSubject("User info")
                .withIssuer("social")
                .withClaim("id", user.getId().toString())
                .withExpiresAt(new Date().toInstant().plus(accessTokenLifetime, ChronoUnit.SECONDS))
                .sign(Algorithm.HMAC256(accessTokenSecret));
    }

    private RefreshToken generateRefreshToken(User user) {

        final LocalDateTime expiryDate = LocalDateTime
                .ofInstant(new Date().toInstant().plus(refreshTokenLifetime, ChronoUnit.SECONDS),
                        ZoneId.systemDefault());

        final RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(RandomUtils.generateRandomString(100))
                .type(AuthStrategyType.BEARER)
                .expiryDate(expiryDate)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }
}
