package ru.nikita.apicourse.api.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import ru.nikita.apicourse.api.authentication.exception.TokenNotPresentAuthException;
import ru.nikita.apicourse.api.response.error.ApiResponseError;
import ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle.BaseExceptionHandler;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;

@Component
public class TokenNotPresentExceptionHandler extends BaseExceptionHandler<TokenNotPresentAuthException> {
    @Override
    public Response handle(HttpServletRequest request, HttpServletResponse response, TokenNotPresentAuthException throwable) {
        return responseMaker.error(ApiResponseError.TOKEN_NOT_PRESENT).build();
    }

    @Override
    public Class<TokenNotPresentAuthException> getExceptionClass() {
        return TokenNotPresentAuthException.class;
    }
}
