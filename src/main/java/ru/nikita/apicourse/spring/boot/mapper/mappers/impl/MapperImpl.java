package ru.nikita.apicourse.spring.boot.mapper.mappers.impl;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.mapper.Mapper;
import ru.nikita.apicourse.spring.boot.mapper.mappers.ObjectMapper;
import ru.nikita.apicourse.spring.boot.mapper.mappers.TargetClassPathExtractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapperImpl implements Mapper {
    private final TargetClassPathExtractor targetClassPathExtractor;
    private final Map<Class<?>, Map<Class<?>, ObjectMapper<?, ?>>> objectMapperMap;

    public MapperImpl(TargetClassPathExtractor targetClassPathExtractor, List<ObjectMapper<?, ?>> objectMappers) {
        this.targetClassPathExtractor = targetClassPathExtractor;
        objectMapperMap = new HashMap<>();
        objectMappers.forEach(objectMapper -> objectMapperMap
                .computeIfAbsent(objectMapper.getTargetClass(), k -> new HashMap<>())
                .putIfAbsent(objectMapper.getResultClass(), objectMapper));
    }

    @Override
    public <T, R> R map(T target, Class<R> resultClass) {

        Class<?> targetClass = targetClassPathExtractor.extract(target);
        final Map<Class<?>, ObjectMapper<?, ?>> targetMappersMap = objectMapperMap.get(targetClass);
        if (null == targetMappersMap || targetMappersMap.isEmpty()) {
            throw new UnsupportedOperationException("Could not found mappers for target class: " + targetClass.getSimpleName());
        }
        ObjectMapper<?, ?> mapper = targetMappersMap.get(resultClass);
        if (null == mapper) {
            throw new UnsupportedOperationException("Could not found mapper from target class " + targetClass.getSimpleName() +
                    " to result class " + resultClass.getSimpleName());
        }
        return ((ObjectMapper<T, R>)mapper).map(target);
    }
}
