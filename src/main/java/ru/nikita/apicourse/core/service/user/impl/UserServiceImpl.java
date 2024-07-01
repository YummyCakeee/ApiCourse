package ru.nikita.apicourse.core.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikita.apicourse.core.exception.EntityNotFoundException;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.repository.user.UserRepository;
import ru.nikita.apicourse.core.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
