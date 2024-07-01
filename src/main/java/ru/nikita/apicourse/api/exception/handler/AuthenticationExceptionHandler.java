package ru.nikita.apicourse.api.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import ru.nikita.apicourse.core.exception.AuthenticationException;
import ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle.BaseExceptionHandler;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;
import ru.nikita.apicourse.spring.boot.restapi.response.error.DefaultError;

@Component
public class AuthenticationExceptionHandler extends BaseExceptionHandler<AuthenticationException> {
    @Override
    public Response handle(HttpServletRequest request, HttpServletResponse response, AuthenticationException throwable) {
        return responseMaker.error(DefaultError.AUTHENTICATION_ERROR).build();
    }

    @Override
    public Class<AuthenticationException> getExceptionClass() {
        return AuthenticationException.class;
    }
}
