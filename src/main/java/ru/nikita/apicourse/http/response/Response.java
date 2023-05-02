package ru.nikita.apicourse.http.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Objects;

public class Response extends ResponseEntity<Object> {

    protected Response(ResponseBuilder responseBuilder) {
        super(HttpStatus.OK);
    }

    public static ResponseBuilder error(){
        return new ErrorResponseBuilder();
    }

    public static ResponseBuilder error(String message){
        return new ErrorResponseBuilder(message);
    }

    protected abstract static class ResponseBuilder {
        protected HttpStatus httpStatus;
        protected Object data;
        protected boolean success;
        public ResponseBuilder() {}

        public ResponseBuilder data(Collection<?> data) {
            this.data = data;
            if (data.isEmpty()) httpStatus = HttpStatus.NO_CONTENT;
            return this;
        }

        public ResponseBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public ResponseBuilder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Response build() {
            if (Objects.isNull(httpStatus))
                httpStatus = getDefaultHttpStatus();
            return new Response(this);
        }

        public abstract HttpStatus getDefaultHttpStatus();
    }
}
