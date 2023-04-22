package ru.nikita.apicourse.exceptions;

public class AuthorizeException extends RuntimeException{
    public AuthorizeException(String message) {
        super(message);
    }
}
