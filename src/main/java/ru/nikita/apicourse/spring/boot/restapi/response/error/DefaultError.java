package ru.nikita.apicourse.spring.boot.restapi.response.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum DefaultError implements Error{

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND),
    BAD_REQUEST_JSON_BODY_EXPECTED(HttpStatus.BAD_REQUEST),
    CONTENT_TYPE_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    AUTHENTICATION_ERROR(HttpStatus.UNAUTHORIZED),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    BAD_REQUEST(HttpStatus.BAD_REQUEST);

    private final HttpStatus status;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
