package ru.nikita.apicourse.api.response.user;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.Creator;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.GsonSerializer;

@Service
public class UserRestEntityCreator implements Creator<UserRestEntity, UserRestEntityPayload> {
    @Override
    public UserRestEntity create(UserRestEntityPayload payload, RestEntityCreator restEntityCreator, GsonSerializer gsonSerializer) {
        return new UserRestEntity(payload.getUser(), restEntityCreator, gsonSerializer);
    }

    @Override
    public Class<UserRestEntity> entityType() {
        return UserRestEntity.class;
    }
}
