package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.extract;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.JsonRestSerializer;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.RestEntity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GsonTypeAdapterExtractorImpl implements GsonTypeAdapterExtractor{
    private final Map<Class<?>, JsonRestSerializer<?>> serializerMap;

    public GsonTypeAdapterExtractorImpl(List<JsonRestSerializer<?>> serializers) {
        serializerMap = serializers.stream().collect(Collectors.toMap(JsonRestSerializer::entityClass, Function.identity()));
    }
    @Override
    public <ENTITY_TYPE> JsonRestSerializer<ENTITY_TYPE> extractFor(RestEntity<ENTITY_TYPE> entity) {
        Class<ENTITY_TYPE> entityTypeClass = entity.entityClass();
        JsonRestSerializer<?> serializer =  serializerMap.get(entityTypeClass);
        if (null == serializer)
            throw new UnsupportedOperationException("There is no suitable serializer for entity class " + entityTypeClass);
        return (JsonRestSerializer<ENTITY_TYPE>) serializer;
    }
}
