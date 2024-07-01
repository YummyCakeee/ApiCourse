package ru.nikita.apicourse.spring.boot.restapi.response.base;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.RestEntity;

import java.util.Collection;

@RequiredArgsConstructor
public abstract class BaseResponseBuilder<BUILDER_TYPE, RESPONSE_TYPE extends Response> {
    protected final GsonSerializer gsonSerializer;
    protected JsonElement data;
    protected HttpStatus httpStatus;
    protected final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

    public BUILDER_TYPE data(RestEntity<?> data) {
        this.data = gsonSerializer.serialize(data);
        return getBuilderObject();
    }

    public BUILDER_TYPE data(Collection<RestEntity<?>> data) {
        this.data = gsonSerializer.serialize(data);
        return getBuilderObject();
    }

    public BUILDER_TYPE addPropertyToData(String property, String value){
        createDataIfNull();
        ((JsonObject) data).addProperty(property, value);

        return getBuilderObject();
    }

    public BUILDER_TYPE httpStatus(HttpStatus status) {
        this.httpStatus = status;
        return getBuilderObject();
    }

    public BUILDER_TYPE header(String key, String value) {
        headers.add(key, value);
        return getBuilderObject();
    }

    protected void createDataIfNull() {
        if (null == data)
            data = new JsonObject();
    }

    public RESPONSE_TYPE build() {
        if (httpStatus == null)
            httpStatus = getDefaultStatus();
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("success", getSuccessValue());
        appendTemplate(responseJson);

        if (data != null)
            responseJson.add("data", data);

        return buildFinalByParams(responseJson, httpStatus, headers);
    }

    protected abstract HttpStatus getDefaultStatus();

    protected abstract RESPONSE_TYPE buildFinalByParams(JsonElement responseJson, HttpStatus status, MultiValueMap<String, String> headers);

    protected abstract boolean getSuccessValue();

    protected void appendTemplate(JsonObject responseJson) {
    }

    protected abstract BUILDER_TYPE getBuilderObject();
}
