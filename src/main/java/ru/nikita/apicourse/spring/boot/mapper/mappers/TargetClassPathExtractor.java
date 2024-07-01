package ru.nikita.apicourse.spring.boot.mapper.mappers;

public interface TargetClassPathExtractor {
    Class<?> extract(Object object);
}
