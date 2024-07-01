package ru.nikita.apicourse.core.service.register.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.nikita.apicourse.core.service.register.RegisterRequest;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class RegisterRequestImpl implements RegisterRequest {
    private final String firstName;
    private final String secondName;
    private final String email;
    private final String password;
}
