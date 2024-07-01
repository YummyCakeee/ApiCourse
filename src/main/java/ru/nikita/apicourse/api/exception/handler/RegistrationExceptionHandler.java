package ru.nikita.apicourse.api.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import ru.nikita.apicourse.core.exception.RegistrationException;
import ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle.BaseExceptionHandler;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;
import ru.nikita.apicourse.spring.boot.restapi.response.error.DefaultError;

@Component
public class RegistrationExceptionHandler extends BaseExceptionHandler<RegistrationException> {
    @Override
    public Response handle(HttpServletRequest request, HttpServletResponse response, RegistrationException throwable) {
        return responseMaker.error(DefaultError.AUTHENTICATION_ERROR).build();
    }

    @Override
    public Class<RegistrationException> getExceptionClass() {
        return RegistrationException.class;
    }
}
