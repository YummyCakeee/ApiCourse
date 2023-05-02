package ru.nikita.apicourse.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.exceptions.EntityNotFoundException;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
