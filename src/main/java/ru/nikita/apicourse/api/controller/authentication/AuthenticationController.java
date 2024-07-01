package ru.nikita.apicourse.api.controller.authentication;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikita.apicourse.api.constants.Endpoints;
import ru.nikita.apicourse.api.request.body.auth.AuthenticateRequestBody;
import ru.nikita.apicourse.api.request.body.auth.RefreshTokenRequestBody;
import ru.nikita.apicourse.api.request.body.register.RegisterRequestBody;
import ru.nikita.apicourse.api.response.auth.AuthResponsePayload;
import ru.nikita.apicourse.core.service.auth.AuthResponse;
import ru.nikita.apicourse.core.service.auth.AuthenticationService;
import ru.nikita.apicourse.core.service.auth.RefreshTokenRequest;
import ru.nikita.apicourse.core.service.register.RegisterRequest;
import ru.nikita.apicourse.core.service.register.RegisterService;
import ru.nikita.apicourse.spring.boot.mapper.Mapper;
import ru.nikita.apicourse.spring.boot.restapi.controller.BaseRestController;
import ru.nikita.apicourse.spring.boot.restapi.response.base.Response;
import ru.nikita.apicourse.spring.boot.serializer.gson.serialize.serializer.RestEntity;


@RestController
@RequestMapping(Endpoints.AUTH)
@RequiredArgsConstructor
public class AuthenticationController extends BaseRestController {
    private final AuthenticationService authenticationService;
    private final RegisterService registerService;
    private final Mapper mapper;


    @PostMapping(Endpoints.REGISTER)
    public Response register(@Valid @RequestBody RegisterRequestBody registerRequestBody) {
        final RegisterRequest registerRequest = mapper.map(registerRequestBody, RegisterRequest.class);
        final AuthResponse authResponse = registerService.register(registerRequest);

        return responseMaker.success().data((RestEntity<?>) entityCreator.create(mapper.map(authResponse, AuthResponsePayload.class))).build();
    }

    @PostMapping(Endpoints.AUTHENTICATE)
    public Response authenticate(@RequestBody AuthenticateRequestBody authenticateRequestBody,
                                 HttpServletResponse response) {

        return responseMaker.success().build();
    }

    @PostMapping(Endpoints.REFRESH_ACCESS_TOKEN)
    public Response refreshAccessToken(@Valid @RequestBody RefreshTokenRequestBody refreshTokenRequestBody) {
        final RefreshTokenRequest refreshTokenRequest = mapper.map(refreshTokenRequestBody, RefreshTokenRequest.class);
        final String accessToken = authenticationService.refresh(refreshTokenRequest);

        return responseMaker.success().addPropertyToData("accessToken", accessToken).build();
    }
}
