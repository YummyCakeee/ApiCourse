package ru.nikita.apicourse.core.service.auth.impl.oauth;

import ru.nikita.apicourse.core.service.auth.AuthResponse;

public interface OAuthTokenResponse extends AuthResponse {
    String getToken();
    String getRefreshToken();
}
