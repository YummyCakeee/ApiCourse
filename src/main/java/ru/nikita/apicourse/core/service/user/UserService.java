package ru.nikita.apicourse.core.service.user;

import ru.nikita.apicourse.core.domain.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);
}
