package ru.nikita.apicourse.core.repository.refreshtoken;

import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.refreshtoken.RefreshToken;
import ru.nikita.apicourse.spring.boot.data.repository.CommonRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CommonRepository<RefreshToken> {
    Optional<RefreshToken> findByTokenAndType(String token, AuthStrategyType type);
}
