package ru.nikita.apicourse.api.response.auth;

import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.RestEntity;

public interface AuthResponseRestEntity<T extends AuthResponseRestEntity<T>> extends RestEntity<T> {
}
