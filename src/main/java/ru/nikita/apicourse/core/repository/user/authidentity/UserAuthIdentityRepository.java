package ru.nikita.apicourse.core.repository.user.authidentity;

import org.springframework.stereotype.Repository;
import ru.nikita.apicourse.core.domain.auth.UserAuthIdentity;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.spring.boot.data.repository.CommonRepository;

import java.util.Optional;

@Repository
public interface UserAuthIdentityRepository extends CommonRepository<UserAuthIdentity> {
    Optional<UserAuthIdentity> findByTypeAndIdentity(AuthStrategyType type, String identity);
}
