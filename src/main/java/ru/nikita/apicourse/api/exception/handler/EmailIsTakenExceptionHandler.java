package ru.nikita.apicourse.api.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import ru.nikita.apicourse.core.exception.EmailIsTakenException;
import ru.nikita.apicourse.spring.boot.restapi.controller.advice.handle.BaseExceptionHandler;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;

import static ru.nikita.apicourse.api.response.error.ApiResponseError.EMAIL_IS_TAKEN;

@Component
public class EmailIsTakenExceptionHandler extends BaseExceptionHandler<EmailIsTakenException> {
    @Override
    public Response handle(HttpServletRequest request, HttpServletResponse response, EmailIsTakenException throwable) {
        return responseMaker.error(EMAIL_IS_TAKEN).build();
    }

    @Override
    public Class<EmailIsTakenException> getExceptionClass() {
        return EmailIsTakenException.class;
    }
}
