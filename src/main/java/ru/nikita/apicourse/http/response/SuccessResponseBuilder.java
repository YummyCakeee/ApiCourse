package ru.nikita.apicourse.http.response;

import org.springframework.http.HttpStatus;

public class SuccessResponseBuilder extends Response.ResponseBuilder {

    public SuccessResponseBuilder() {
        success = true;
        httpStatus = getDefaultHttpStatus();
    }

    @Override
    public HttpStatus getDefaultHttpStatus() {
        return HttpStatus.OK;
    }
}
