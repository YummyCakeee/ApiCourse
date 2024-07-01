package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer;

import com.google.gson.JsonArray;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.extract.GsonTypeAdapterExtractor;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionSerializer extends AbstractSerializer {
    private final Collection<? extends RestEntity<?>> data;

    public CollectionSerializer(GsonTypeAdapterExtractor extractor, Collection<? extends RestEntity<?>> data) {
        super(extractor);
        this.data = data;
    }

    public JsonArray toJson() {
        return null == data ?
                new JsonArray() :
                data.stream()
                        .filter(Objects::nonNull)
                        .map(super::toJsonTree)
                        .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);
    }
}
