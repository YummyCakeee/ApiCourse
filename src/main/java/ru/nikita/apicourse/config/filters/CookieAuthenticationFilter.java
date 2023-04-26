package ru.nikita.apicourse.config.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CookieAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTH_COOKIE_NAME = "API_COURSE";
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        Optional<Cookie> cookieAuth = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> cookie.getName().equals(AUTH_COOKIE_NAME)).findFirst();

        cookieAuth.ifPresent(cookie -> {

                    Authentication authentication = authenticationManager
                            .authenticate(new PreAuthenticatedAuthenticationToken(cookie.getValue(), null));

                    SecurityContextHolder.getContext().setAuthentication(
                            authentication
                    );
                }
        );

        filterChain.doFilter(request, response);
    }

}
