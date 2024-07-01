package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer;

import com.google.gson.JsonElement;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.extract.GsonTypeAdapterExtractor;

public class EntitySerializer extends AbstractSerializer {

    private final RestEntity<?> data;

    public EntitySerializer(GsonTypeAdapterExtractor extractor, RestEntity<?> data) {
        super(extractor);
        this.data = data;
    }

    public JsonElement toJson() {
        return super.toJsonTree(this.data);
    }
}
