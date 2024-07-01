package ru.nikita.apicourse.core.service.auth.impl.oauth.bearer.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.service.auth.impl.oauth.OAuthTokenResponse;

@Getter
@Setter
@RequiredArgsConstructor
public class BearerOAuthTokenResponse implements OAuthTokenResponse {
    private final String token;
    private final String refreshToken;

    private final String id;
    private final String firstName;
    private final String secondName;
    private final String email;

    private User user;
}
