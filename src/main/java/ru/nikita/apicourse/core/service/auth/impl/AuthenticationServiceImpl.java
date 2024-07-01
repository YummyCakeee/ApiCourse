package ru.nikita.apicourse.core.service.auth.impl;

import org.springframework.stereotype.Service;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.core.exception.AuthorizeException;
import ru.nikita.apicourse.core.exception.RefreshTokenAuthorizeException;
import ru.nikita.apicourse.core.exception.UserNotFoundException;
import ru.nikita.apicourse.core.repository.user.UserRepository;
import ru.nikita.apicourse.core.service.auth.AuthRequest;
import ru.nikita.apicourse.core.service.auth.AuthStrategy;
import ru.nikita.apicourse.core.service.auth.AuthenticationService;
import ru.nikita.apicourse.core.service.auth.RefreshTokenRequest;
import ru.nikita.apicourse.core.service.auth.exception.NotSupportedStrategyTypeAuthorizeException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final Map<AuthStrategyType, AuthStrategy<? extends AuthRequest>> strategyMap;

    public AuthenticationServiceImpl(UserRepository userRepository, List<AuthStrategy<? extends AuthRequest>> strategies) {
        this.userRepository = userRepository;
        this.strategyMap = strategies.stream().collect(Collectors.toMap(AuthStrategy::getStrategyType, Function.identity()));
    }


    @Override
    public User findById(Long userId) throws AuthorizeException {
        return userRepository.findById(userId).orElseThrow(AuthorizeException::new);
    }

    @Override
    public User findUserBy(AuthStrategyType strategyType, String token) throws AuthorizeException {
        return getStrategy(strategyType).findUserBy(token).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public String refresh(RefreshTokenRequest request) throws RefreshTokenAuthorizeException {
        return getStrategy(request.getStrategyType()).refresh(request);
    }

    private AuthStrategy<? extends AuthRequest> getStrategy(AuthStrategyType strategyType) {
        return Optional.ofNullable(strategyMap.get(strategyType))
                .orElseThrow(() -> new NotSupportedStrategyTypeAuthorizeException(strategyType));
    }
}
