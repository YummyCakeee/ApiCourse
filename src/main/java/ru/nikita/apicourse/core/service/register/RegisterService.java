package ru.nikita.apicourse.core.service.register;

import ru.nikita.apicourse.core.service.auth.AuthResponse;

public interface RegisterService {
    AuthResponse register(RegisterRequest registerRequest);
}
