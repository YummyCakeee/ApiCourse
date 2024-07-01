package ru.nikita.apicourse.spring.boot.restapi.response.base;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public abstract class Response extends ResponseEntity<String> {

    protected Response(String body, HttpStatus status) {
        this(body, status, new LinkedMultiValueMap<>());
    }

    protected Response(String body, HttpStatus status, MultiValueMap<String, String> headers) {
        super(body, getCommonHeaders(headers), status);
    }


    private static HttpHeaders getCommonHeaders(MultiValueMap<String, String> customHeaders) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        responseHeaders.add("Access-Control-Allow-Headers", "Content-Type");
        responseHeaders.add("Access-Control-Allow-Methods", "Get, POST, PUT, PATCH, DELETE, OPTIONS");
        responseHeaders.set("Cache-Control", "no-cache, no-store, must-revalidate");
        responseHeaders.set("Pragma", "no-cache");

        responseHeaders.addAll(customHeaders);

        return responseHeaders;
    }
}
