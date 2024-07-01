package ru.nikita.apicourse.spring.boot.restapi.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;

public interface ExceptionHandler<T extends Throwable> {

    Response handle(HttpServletRequest request, HttpServletResponse response, T throwable);

    default Response handleException(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        return handle(request, response, (T) throwable);
    }

    Class<T> getExceptionClass();
}
