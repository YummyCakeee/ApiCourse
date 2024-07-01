package ru.nikita.apicourse.spring.boot.restapi.response;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.restapi.response.error.Error;
import ru.nikita.apicourse.spring.boot.restapi.response.error.ErrorResponse;
import ru.nikita.apicourse.spring.boot.restapi.response.success.SuccessResponse;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

@Service
@RequiredArgsConstructor
public class ResponseMakerImpl implements ResponseMaker{
    private final GsonSerializer gsonSerializer;
    @Override
    public SuccessResponse.Builder success() {
        return new SuccessResponse.Builder(gsonSerializer);
    }

    @Override
    public ErrorResponse.Builder error() {
        return new ErrorResponse.Builder(gsonSerializer);
    }

    @Override
    public ErrorResponse.Builder error(Error error) {
        return new ErrorResponse.Builder(gsonSerializer).error(error);
    }
}
