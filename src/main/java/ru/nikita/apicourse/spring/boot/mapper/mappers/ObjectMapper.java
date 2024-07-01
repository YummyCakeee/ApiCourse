package ru.nikita.apicourse.spring.boot.mapper.mappers;

public interface ObjectMapper<T, R> {

    R map(T source);

    Class<T> getTargetClass();
    Class<R> getResultClass();
}
