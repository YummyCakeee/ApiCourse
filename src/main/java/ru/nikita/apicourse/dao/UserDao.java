package ru.nikita.apicourse.dao;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDao {
    private Long id;
    private String email;
    private String firstName;
    private String secondName;
    private String password;
}
