package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer;

import com.google.gson.JsonSerializer;

public interface JsonRestSerializer<ENTITY_TYPE> extends JsonSerializer<ENTITY_TYPE> {
    Class<ENTITY_TYPE> entityClass();
}
