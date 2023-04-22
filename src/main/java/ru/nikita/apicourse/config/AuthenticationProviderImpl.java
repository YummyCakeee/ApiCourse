package ru.nikita.apicourse.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.services.AuthenticationService;

import java.util.Collections;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = authenticationService.findByToken((String) authentication.getPrincipal());
        if (Objects.nonNull(user)) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class);
    }
}
