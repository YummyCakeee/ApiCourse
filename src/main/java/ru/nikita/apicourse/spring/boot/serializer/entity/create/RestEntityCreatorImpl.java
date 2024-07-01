package ru.nikita.apicourse.spring.boot.serializer.entity.create;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.Creator;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.payload.Payload;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RestEntityCreatorImpl implements RestEntityCreator {
    private final GsonSerializer gsonSerializer;
    private final Map<Class<?>, Creator<?, ?>> creatorMap;

    public RestEntityCreatorImpl(List<Creator<?, ?>> creators, GsonSerializer gsonSerializer) {
        this.gsonSerializer = gsonSerializer;
        creatorMap = creators.stream().collect(Collectors.toMap(Creator::entityType, Function.identity()));
    }

    @Override
    public <REST_ENTITY, PAYLOAD extends Payload<REST_ENTITY>> REST_ENTITY create(PAYLOAD payload) {
        if (null == payload)
            throw new IllegalArgumentException("Payload can not be empty");
        Class<REST_ENTITY> entityClass = payload.entityClass();
        Creator<?, ?> creator = creatorMap.get(entityClass);
        if (null == creator)
            throw new UnsupportedOperationException("THere is no suitable entity creator for type " + entityClass);
        return (REST_ENTITY) creator.castAndCreate(payload, this, gsonSerializer);
    }
}
