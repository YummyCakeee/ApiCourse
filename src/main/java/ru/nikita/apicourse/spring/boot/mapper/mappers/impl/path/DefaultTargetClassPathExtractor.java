package ru.nikita.apicourse.spring.boot.mapper.mappers.impl.path;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.mapper.mappers.TargetClassPathExtractor;

@Service
public class DefaultTargetClassPathExtractor implements TargetClassPathExtractor {
    @Override
    public Class<?> extract(Object object) {
        return null;
    }
}
