package ru.nikita.apicourse.api.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import ru.nikita.apicourse.api.response.error.ApiResponseError;
import ru.nikita.apicourse.core.exception.PermissionDeniedException;
import ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle.BaseExceptionHandler;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;

@Component
public class PermissionDeniedExceptionHandler extends BaseExceptionHandler<PermissionDeniedException> {
    @Override
    public Response handle(HttpServletRequest request, HttpServletResponse response, PermissionDeniedException throwable) {
        return responseMaker.error(ApiResponseError.PERMISSION_DENIED).build();
    }

    @Override
    public Class<PermissionDeniedException> getExceptionClass() {
        return PermissionDeniedException.class;
    }
}
