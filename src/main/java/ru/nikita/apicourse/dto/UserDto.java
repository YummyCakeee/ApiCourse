package ru.nikita.apicourse.dto;

import lombok.*;
import ru.nikita.apicourse.models.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String secondName;
    private Role role;
}
