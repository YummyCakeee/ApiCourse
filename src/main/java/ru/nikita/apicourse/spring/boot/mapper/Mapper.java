package ru.nikita.apicourse.spring.boot.mapper;

public interface Mapper {
    <T, R> R map(T target, Class<R> resultClass);
}
