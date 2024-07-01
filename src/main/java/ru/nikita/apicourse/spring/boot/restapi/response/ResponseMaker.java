package ru.nikita.apicourse.spring.boot.restapi.response;

import ru.nikita.apicourse.spring.boot.restapi.response.error.Error;
import ru.nikita.apicourse.spring.boot.restapi.response.error.ErrorResponse;
import ru.nikita.apicourse.spring.boot.restapi.response.success.SuccessResponse;

public interface ResponseMaker {
    SuccessResponse.Builder success();
    ErrorResponse.Builder error();
    ErrorResponse.Builder error(Error error);
}
