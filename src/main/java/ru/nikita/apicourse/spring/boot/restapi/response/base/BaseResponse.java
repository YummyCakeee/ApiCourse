package ru.nikita.apicourse.spring.boot.restapi.response.base;

import com.google.gson.JsonElement;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public abstract class BaseResponse extends Response{
    protected BaseResponse(JsonElement body, HttpStatus status, MultiValueMap<String, String> headers) {
        super(body.toString(), status, headers);
    }
}
