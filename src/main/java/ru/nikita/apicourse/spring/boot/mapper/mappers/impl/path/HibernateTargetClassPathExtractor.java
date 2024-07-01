package ru.nikita.apicourse.spring.boot.mapper.mappers.impl.path;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.mapper.mappers.TargetClassPathExtractor;

@Primary
@Service
public class HibernateTargetClassPathExtractor implements TargetClassPathExtractor {
    @Override
    public Class<?> extract(Object object) {
        return object instanceof HibernateProxy ? ((HibernateProxy)object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
    }
}
