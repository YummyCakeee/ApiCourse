package ru.nikita.apicourse.spring.boot.restapi.response.success;

import com.google.gson.JsonElement;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import ru.nikita.apicourse.spring.boot.restapi.response.base.BaseResponse;
import ru.nikita.apicourse.spring.boot.restapi.response.base.BaseResponseBuilder;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

public class SuccessResponse extends BaseResponse {
    protected SuccessResponse(JsonElement body, HttpStatus status, MultiValueMap<String, String> headers) {
        super(body, status, headers);
    }

    public static class Builder extends BaseResponseBuilder<Builder, SuccessResponse> {

        public Builder(GsonSerializer gsonSerializer) {
            super(gsonSerializer);
        }

        @Override
        protected HttpStatus getDefaultStatus() {
            return HttpStatus.OK;
        }

        @Override
        protected SuccessResponse buildFinalByParams(JsonElement responseJson, HttpStatus status, MultiValueMap<String, String> headers) {
            return new SuccessResponse(responseJson, status, headers);
        }

        @Override
        protected boolean getSuccessValue() {
            return true;
        }

        @Override
        protected Builder getBuilderObject() {
            return this;
        }
    }
}
