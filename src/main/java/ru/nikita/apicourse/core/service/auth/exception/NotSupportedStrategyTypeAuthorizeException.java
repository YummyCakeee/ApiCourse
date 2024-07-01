package ru.nikita.apicourse.core.service.auth.exception;

import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.exception.AuthorizeException;

public class NotSupportedStrategyTypeAuthorizeException extends AuthorizeException {
    public NotSupportedStrategyTypeAuthorizeException(AuthStrategyType strategyType) {
        super("Strategy type " + strategyType + " not supported");
    }
}
