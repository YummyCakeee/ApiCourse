package ru.nikita.apicourse.spring.boot.serializer.entity.create.creator;

import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.payload.Payload;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

public interface Creator<REST_ENTITY, PAYLOAD extends Payload<REST_ENTITY>> {
    REST_ENTITY create(PAYLOAD payload, RestEntityCreator restEntityCreator, GsonSerializer gsonSerializer);
    Class<REST_ENTITY> entityType();
    default REST_ENTITY castAndCreate(Object payload, RestEntityCreator restEntityCreator, GsonSerializer gsonSerializer) {
        return create((PAYLOAD) payload, restEntityCreator, gsonSerializer);
    }
}
