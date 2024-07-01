package ru.nikita.apicourse.core.service.user.password.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.domain.user.password.UserPassword;
import ru.nikita.apicourse.core.repository.user.password.UserPasswordRepository;
import ru.nikita.apicourse.core.service.user.password.UserPasswordService;

@Service
@RequiredArgsConstructor
public class UserPasswordServiceImpl implements UserPasswordService {
    private final PasswordEncoder passwordEncoder;
    private final UserPasswordRepository userPasswordRepository;
    @Override
    public String generatePasswordHash(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean passwordMatches(User user, String password){
        return userPasswordRepository.findByUserId(user.getId())
                .map(userPassword -> passwordEncoder.matches(password, userPassword.getPasswordHash()))
                .orElse(false);
    }

    @Override
    public UserPassword save(User user, String password) {
        final UserPassword userPassword = new UserPassword();
        userPassword.setUser(user);
        userPassword.setPasswordHash(generatePasswordHash(password));

        return userPasswordRepository.save(userPassword);
    }
}
