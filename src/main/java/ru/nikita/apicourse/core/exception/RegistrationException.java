package ru.nikita.apicourse.core.exception;

public class RegistrationException extends RuntimeException{
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException() {
    }
}
