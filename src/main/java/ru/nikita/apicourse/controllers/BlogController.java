package ru.nikita.apicourse.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;;
import static ru.nikita.apicourse.constants.Endpoints.API_ROOT;

@RestController
@RequestMapping( API_ROOT + "/blogs")
public class BlogController {
}
