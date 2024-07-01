package ru.nikita.apicourse.api.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import ru.nikita.apicourse.core.exception.AuthorizeException;
import ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle.BaseExceptionHandler;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;
import ru.nikita.apicourse.spring.boot.restapi.response.error.DefaultError;

@Component
public class AuthorizeExceptionHandler extends BaseExceptionHandler<AuthorizeException> {
    @Override
    public Response handle(HttpServletRequest request, HttpServletResponse response, AuthorizeException throwable) {
        return responseMaker.error(DefaultError.AUTHENTICATION_ERROR).build();
    }

    @Override
    public Class<AuthorizeException> getExceptionClass() {
        return AuthorizeException.class;
    }
}
