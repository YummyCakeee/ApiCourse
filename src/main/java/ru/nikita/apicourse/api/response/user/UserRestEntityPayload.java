package ru.nikita.apicourse.api.response.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.creator.payload.Payload;

@Getter
@RequiredArgsConstructor
public class UserRestEntityPayload implements Payload<UserRestEntity> {
    private final User user;
    @Override
    public Class<UserRestEntity> entityClass() {
        return UserRestEntity.class;
    }
}
