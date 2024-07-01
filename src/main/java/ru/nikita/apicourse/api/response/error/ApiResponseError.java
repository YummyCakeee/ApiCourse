package ru.nikita.apicourse.api.response.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import ru.nikita.apicourse.spring.boot.restapi.response.error.Error;

@RequiredArgsConstructor
public enum ApiResponseError implements Error {

    USERNAME_NOT_FOUND(HttpStatus.BAD_REQUEST),
    PERMISSION_DENIED(HttpStatus.FORBIDDEN),
    TOKEN_NOT_PRESENT(HttpStatus.UNAUTHORIZED),
    EMAIL_IS_TAKEN(HttpStatus.BAD_REQUEST);

    private final HttpStatus status;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }
}
