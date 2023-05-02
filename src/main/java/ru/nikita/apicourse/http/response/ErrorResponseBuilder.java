package ru.nikita.apicourse.http.response;

import org.springframework.http.HttpStatus;

public class ErrorResponseBuilder extends Response.ResponseBuilder {

    private final String message;
    public ErrorResponseBuilder() {
        message = "";
        success = false;
        httpStatus = getDefaultHttpStatus();
    }
    public ErrorResponseBuilder(String message) {
        this.message = message;
        success = false;
        httpStatus = getDefaultHttpStatus();
    }

    @Override
    public HttpStatus getDefaultHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
