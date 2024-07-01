package ru.nikita.apicourse.core.service.user.password;

import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.domain.user.password.UserPassword;

public interface UserPasswordService {
    String generatePasswordHash(String password);

    boolean passwordMatches(User user, String password);

    UserPassword save(User user, String password);
}
