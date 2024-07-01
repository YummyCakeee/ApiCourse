package ru.nikita.apicourse.api.response.auth;

import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.payload.Payload;

public interface AuthResponsePayload<T extends AuthResponseRestEntity<T>> extends Payload<T> {
}
