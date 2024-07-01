package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Collection;

public interface GsonSerializer {
    JsonElement serialize(RestEntity<?> restEntity);
    JsonArray serialize(Collection<RestEntity<?>> restEntities);
}
