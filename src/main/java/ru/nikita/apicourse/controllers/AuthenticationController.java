package ru.nikita.apicourse.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikita.apicourse.exceptions.AuthenticationException;
import ru.nikita.apicourse.exceptions.RegistrationException;
import ru.nikita.apicourse.http.request.AuthenticateRequest;
import ru.nikita.apicourse.http.request.RegisterRequest;
import ru.nikita.apicourse.http.response.BasicResponse;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.services.AuthenticationService;

import static ru.nikita.apicourse.config.filters.CookieAuthenticationFilter.AUTH_COOKIE_NAME;
import static ru.nikita.apicourse.constants.Endpoints.API_ROOT;

@RestController
@RequestMapping(API_ROOT + "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<BasicResponse> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authenticationService.register(registerRequest);
        } catch (RegistrationException ex) {
            return new ResponseEntity<>(new BasicResponse(false, ex.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BasicResponse(true), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<BasicResponse> authenticate(@RequestBody AuthenticateRequest authenticateRequest,
                                                      HttpServletResponse response) {
        try {
            User user = authenticationService.authenticate(authenticateRequest);
            Cookie cookie = new Cookie(AUTH_COOKIE_NAME, authenticationService.generateToken(user));
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return new ResponseEntity<>(new BasicResponse(true), HttpStatus.OK);
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(new BasicResponse(false, "Invalid login or password"), HttpStatus.BAD_REQUEST);
        }
    }
}
