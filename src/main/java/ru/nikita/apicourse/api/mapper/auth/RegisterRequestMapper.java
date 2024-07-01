package ru.nikita.apicourse.api.mapper.auth;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.api.request.body.register.RegisterRequestBody;
import ru.nikita.apicourse.core.service.register.RegisterRequest;
import ru.nikita.apicourse.core.service.register.impl.RegisterRequestImpl;
import ru.nikita.apicourse.spring.boot.mapper.mappers.ObjectMapper;

@Service
public class RegisterRequestMapper implements ObjectMapper<RegisterRequestBody, RegisterRequest> {
    @Override
    public RegisterRequestImpl map(RegisterRequestBody registerRequestBody) {
        return RegisterRequestImpl.builder()
                .firstName(registerRequestBody.getFirstName())
                .secondName(registerRequestBody.getSecondName())
                .email(registerRequestBody.getEmail())
                .password(registerRequestBody.getPassword())
                .build();
    }

    @Override
    public Class<RegisterRequestBody> getTargetClass() {
        return RegisterRequestBody.class;
    }

    @Override
    public Class<RegisterRequest> getResultClass() {
        return RegisterRequest.class;
    }
}
