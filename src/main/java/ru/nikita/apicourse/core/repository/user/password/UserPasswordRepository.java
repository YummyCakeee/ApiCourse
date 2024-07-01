package ru.nikita.apicourse.core.repository.user.password;

import org.springframework.stereotype.Repository;
import ru.nikita.apicourse.core.domain.user.password.UserPassword;
import ru.nikita.apicourse.spring.boot.data.repository.CommonRepository;

import java.util.Optional;

@Repository
public interface UserPasswordRepository extends CommonRepository<UserPassword> {
    Optional<UserPassword> findByUserId(Long userId);
}
