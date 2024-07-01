package ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer;

public interface RestEntity<T> {
    Class<T> entityClass();
}
