package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.extract.GsonTypeAdapterExtractor;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GsonSerializerImpl implements GsonSerializer{
    private final GsonTypeAdapterExtractor extractor;
    @Override
    public JsonElement serialize(RestEntity<?> data) {
        return new EntitySerializer(extractor, data).toJson();
    }

    @Override
    public JsonArray serialize(Collection<RestEntity<?>> data) {
        return new CollectionSerializer(extractor, data).toJson();
    }
}
