package ru.nikita.apicourse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikita.apicourse.dao.UserDao;
import ru.nikita.apicourse.exceptions.RegistrationException;
import ru.nikita.apicourse.http.response.BasicResponse;
import ru.nikita.apicourse.mapper.UserMapper;
import ru.nikita.apicourse.services.RegistrationService;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("")
    public ResponseEntity<BasicResponse> register(@RequestBody UserDao userDao) {
        try {
            registrationService.register(UserMapper.userDaoToUser(userDao));
        } catch (RegistrationException ex) {
            return new ResponseEntity<>(new BasicResponse(false, ex.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BasicResponse(true), HttpStatus.OK);
    }
}
