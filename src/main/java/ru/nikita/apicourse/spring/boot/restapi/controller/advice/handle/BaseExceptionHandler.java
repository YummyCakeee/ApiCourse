package ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle;

import org.springframework.beans.factory.annotation.Autowired;
import ru.nikita.apicourse.spring.boot.restapi.controller.advice.ExceptionHandler;
import ru.nikita.apicourse.spring.boot.restapi.response.ResponseMaker;

public abstract class BaseExceptionHandler<T extends Throwable> implements ExceptionHandler<T> {
    @Autowired
    protected ResponseMaker responseMaker;
}
