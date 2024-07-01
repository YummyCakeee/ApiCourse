package ru.nikita.apicourse.core.service.register.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikita.apicourse.core.domain.auth.UserAuthIdentity;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.exception.EmailIsTakenException;
import ru.nikita.apicourse.core.repository.user.UserRepository;
import ru.nikita.apicourse.core.repository.user.authidentity.UserAuthIdentityRepository;
import ru.nikita.apicourse.core.service.auth.AuthResponse;
import ru.nikita.apicourse.core.service.auth.impl.oauth.bearer.response.BearerOAuthTokenResponse;
import ru.nikita.apicourse.core.service.auth.jwt.JWTService;
import ru.nikita.apicourse.core.service.register.RegisterRequest;
import ru.nikita.apicourse.core.service.register.RegisterService;
import ru.nikita.apicourse.core.service.user.password.UserPasswordService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final UserAuthIdentityRepository userAuthIdentityRepository;
    private final UserPasswordService userPasswordService;
    private final JWTService jwtService;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new EmailIsTakenException();
        }

        final User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setSecondName(registerRequest.getSecondName());
        user.setEmail(registerRequest.getEmail());

        userRepository.save(user);

        BearerOAuthTokenResponse response  = jwtService.generateToken(user);
        response.setUser(user);

        final UserAuthIdentity userAuthIdentity = new UserAuthIdentity();
        userAuthIdentity.setUser(user);
        userAuthIdentity.setIdentity(response.getId());
        userAuthIdentity.setType(AuthStrategyType.BEARER);

        userAuthIdentityRepository.save(userAuthIdentity);
        userPasswordService.save(user, registerRequest.getPassword());

        return response;
    }
}
