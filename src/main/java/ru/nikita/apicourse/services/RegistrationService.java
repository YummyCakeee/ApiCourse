package ru.nikita.apicourse.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.exceptions.RegistrationException;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;

    public void register(User user) throws RegistrationException {
        if (userRepository.findFirstByEmail(user.getEmail()).isPresent())
            throw new RegistrationException("This email is already taken");
        user.setId(null);
        userRepository.save(user);
    }
}
