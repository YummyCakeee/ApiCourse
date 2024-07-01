package ru.nikita.apicourse.spring.boot.restapi.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nikita.apicourse.spring.boot.restapi.response.ResponseMaker;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;
import ru.nikita.apicourse.spring.boot.restapi.response.error.DefaultError;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionControllerAdvice {
    private final ResponseMaker responseMaker;
    private final Map<Class<?>, ExceptionHandler<?>> handlerMap;

    public GlobalExceptionControllerAdvice(ResponseMaker responseMaker, List<ExceptionHandler<?>> exceptionHandlers) {
        this.responseMaker = responseMaker;
        this.handlerMap = exceptionHandlers.stream().collect(Collectors.toMap(ExceptionHandler::getExceptionClass, Function.identity()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public Response globalExceptionControllerAdvice(HttpServletRequest request, HttpServletResponse response, Throwable exception) {
        Throwable throwable = exception;
        Class<? extends Throwable> exceptionClass = exception.getClass();

        if (exception instanceof InvocationTargetException) {
            throwable = exception.getCause();
            exceptionClass = exception.getCause().getClass();
        }

        ExceptionHandler<?> exceptionHandler = handlerMap.get(exceptionClass);

        if (null == exceptionHandler) {
            String method = request.getMethod();
            String requestURI = request.getRequestURI();
            log.error("GLOBAL ERROR EXCEPTION {} - {}", method, requestURI, exception);
            return responseMaker.error(DefaultError.INTERNAL_ERROR).build();
        }

        return exceptionHandler.handleException(request, response, throwable);
    }
}
