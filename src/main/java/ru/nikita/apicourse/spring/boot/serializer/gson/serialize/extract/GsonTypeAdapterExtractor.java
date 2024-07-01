package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.extract;

import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.JsonRestSerializer;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.RestEntity;

public interface GsonTypeAdapterExtractor {
    <ENTITY_TYPE>JsonRestSerializer<ENTITY_TYPE> extractFor(RestEntity<ENTITY_TYPE> restEntity);
}
