package ru.nikita.apicourse.api.authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;
import ru.nikita.apicourse.api.authentication.exception.TokenNotPresentAuthException;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.exception.AuthorizeException;
import ru.nikita.apicourse.core.exception.PermissionDeniedException;
import ru.nikita.apicourse.core.service.auth.AuthenticationService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION_KEY_NAME = "authorization";

    private final AuthenticationService authenticationService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthUser.class) && parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        final AuthUser annotation = parameter.getParameterAnnotation(AuthUser.class);

        final boolean required = annotation.required();
        final List<Permission> requiredPermissions = Arrays.stream(annotation.has()).toList();

        final HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Optional<String> authValueOptional = extractAuthorizationValueFromRequest(webRequest, httpServletRequest);

        if (authValueOptional.isEmpty()) {
            if (required)
                throw new TokenNotPresentAuthException();
            return null;
        }

        final String[] authInfo = authValueOptional.get().split(" ");
        AuthStrategyType strategyType = AuthStrategyType.fromShortName(authInfo[0]);
        String token = authInfo[1];

        User user = null;

        try {
            user = authenticationService.findUserBy(strategyType, token);
        } catch (AuthorizeException e) {
            if (required) throw e;
        }

        if (requiredPermissions.isEmpty()) {
            for (Permission permission : requiredPermissions) {
                if (!user.can(permission.action(), permission.object()))
                    throw new PermissionDeniedException();
            }
        }
        
        return user;
    }

    private Optional<String> extractAuthorizationValueFromRequest(@NonNull final NativeWebRequest nativeWebRequest,
                                                                  final HttpServletRequest httpServletRequest) {
        return extractAuthorizationValueFromRequestByCookie(httpServletRequest)
                .or(() -> extractAuthorizationValueFromRequestByHeader(nativeWebRequest));
    }

    private Optional<String> extractAuthorizationValueFromRequestByCookie(final HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest)
                .map(request -> WebUtils.getCookie(request, AUTHORIZATION_KEY_NAME))
                .map(Cookie::getValue);
    }

    private Optional<String> extractAuthorizationValueFromRequestByHeader(@NonNull final NativeWebRequest nativeWebRequest) {
        return Optional.ofNullable(nativeWebRequest.getHeader(AUTHORIZATION_KEY_NAME));
    }
}
