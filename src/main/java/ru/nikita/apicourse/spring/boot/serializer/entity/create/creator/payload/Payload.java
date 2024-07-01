package ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.payload;

public interface Payload<ENTITY_TYPE> {
    Class<ENTITY_TYPE> entityClass();
}
