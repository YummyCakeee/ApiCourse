package ru.nikita.apicourse.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikita.apicourse.constants.Endpoints;

import static ru.nikita.apicourse.constants.Endpoints.API_ROOT;

@RestController()
@RequestMapping(API_ROOT)
public class AuthController {
}
