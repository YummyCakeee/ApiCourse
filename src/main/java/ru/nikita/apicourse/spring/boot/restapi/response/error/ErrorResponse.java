package ru.nikita.apicourse.spring.boot.restapi.response.error;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import ru.nikita.apicourse.spring.boot.restapi.response.base.BaseResponse;
import ru.nikita.apicourse.spring.boot.restapi.response.base.BaseResponseBuilder;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

import java.util.Optional;

public class ErrorResponse extends BaseResponse {

    protected ErrorResponse(JsonElement body, HttpStatus status, MultiValueMap<String, String> headers) {
        super(body, status, headers);
    }

    public static class Builder extends BaseResponseBuilder<Builder, ErrorResponse> {
        private static final Error DEFAULT_ERROR;

        private Error error;

        static {
            DEFAULT_ERROR = DefaultError.BAD_REQUEST;
        }

        public Builder(GsonSerializer gsonSerializer) {
            super(gsonSerializer);
            setError(DEFAULT_ERROR);
        }

        private void setError(Error error) {
            this.error = error;
            this.httpStatus = error.getStatus();
        }

        public Builder error(Error error) {
            setError(error);
            return this;
        }

        @Override
        protected HttpStatus getDefaultStatus() {
            return DEFAULT_ERROR.getStatus();
        }

        @Override
        protected ErrorResponse buildFinalByParams(JsonElement responseJson, HttpStatus status, MultiValueMap<String, String> headers) {
            return new ErrorResponse(responseJson, status, headers);
        }

        @Override
        protected boolean getSuccessValue() {
            return false;
        }

        @Override
        protected Builder getBuilderObject() {
            return this;
        }

        @Override
        protected void appendTemplate(JsonObject responseJson) {
            responseJson.addProperty("error", error.getName());
            Optional.ofNullable(error.message()).filter(Strings::isNotEmpty)
                    .ifPresent(s -> responseJson.addProperty("message", s));
            super.appendTemplate(responseJson);
        }
    }
}
