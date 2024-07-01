package ru.nikita.apicourse.spring.boot.serializer.entity.create;

import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.payload.Payload;

public interface RestEntityCreator {
    <REST_ENTITY, PAYLOAD extends Payload<REST_ENTITY>> REST_ENTITY create(PAYLOAD payload);
}
