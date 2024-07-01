package ru.nikita.apicourse.api.response.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.RestEntity;

@Getter
@RequiredArgsConstructor
public class UserRestEntity implements RestEntity<UserRestEntity> {
    private final User user;
    private final RestEntityCreator restEntityCreator;
    private final GsonSerializer gsonSerializer;

    @Override
    public Class<UserRestEntity> entityClass() {
        return UserRestEntity.class;
    }
}
