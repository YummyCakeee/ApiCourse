package ru.nikita.apicourse.mapper;

import ru.nikita.apicourse.dto.UserDto;
import ru.nikita.apicourse.models.User;

public class UserMapper {

    public static UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static User userDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .secondName(userDto.getSecondName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();
    }
}
