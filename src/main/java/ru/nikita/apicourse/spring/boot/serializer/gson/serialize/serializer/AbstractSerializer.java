package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.extract.GsonTypeAdapterExtractor;

@RequiredArgsConstructor
public abstract class AbstractSerializer {
    private final GsonTypeAdapterExtractor extractor;

    protected <T>JsonElement toJsonTree(RestEntity<T> entity) {
        return null == entity ?
                new JsonObject() :
                new GsonBuilder().registerTypeAdapter(entity.entityClass(), extractor.extractFor(entity)).create().toJsonTree(entity);
    }
}
