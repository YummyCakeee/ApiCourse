package ru.nikita.apicourse.api.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import ru.nikita.apicourse.core.exception.EntityNotFoundException;
import ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle.BaseExceptionHandler;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;
import ru.nikita.apicourse.spring.boot.restapi.response.error.DefaultError;

@Component
public class EntityNotFoundExceptionHandler extends BaseExceptionHandler<EntityNotFoundException> {
    @Override
    public Response handle(HttpServletRequest request, HttpServletResponse response, EntityNotFoundException throwable) {
        return responseMaker.error(DefaultError.NOT_FOUND).build();
    }

    @Override
    public Class<EntityNotFoundException> getExceptionClass() {
        return EntityNotFoundException.class;
    }
}
