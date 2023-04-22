package ru.nikita.apicourse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikita.apicourse.dto.UserDto;
import ru.nikita.apicourse.http.response.DataCollectionResponse;
import ru.nikita.apicourse.mapper.UserMapper;
import ru.nikita.apicourse.models.User;
import ru.nikita.apicourse.services.UserService;

import java.util.List;

import static ru.nikita.apicourse.constants.Endpoints.API_ROOT;

@RestController()
@RequestMapping(API_ROOT + "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<DataCollectionResponse> getAll() {

        List<UserDto> users = userService.getAllUsers().stream().map(UserMapper::userToUserDto).toList();
        DataCollectionResponse response = new DataCollectionResponse(users);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
