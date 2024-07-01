package ru.nikita.apicourse.spring.boot.restapi.response.error;

import org.springframework.http.HttpStatus;

public interface Error {
    String getName();

    HttpStatus getStatus();

    default String message() {
        return null;
    }
}
