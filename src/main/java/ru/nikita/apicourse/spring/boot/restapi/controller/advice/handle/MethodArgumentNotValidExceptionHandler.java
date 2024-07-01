package ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;

public class MethodArgumentNotValidExceptionHandler extends BaseExceptionHandler<MethodArgumentNotValidException> {
    @Override
    public Response handle(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException throwable) {
        return ex;
    }

    @Override
    public Class<MethodArgumentNotValidException> getExceptionClass() {
        return MethodArgumentNotValidException.class;
    }
}
