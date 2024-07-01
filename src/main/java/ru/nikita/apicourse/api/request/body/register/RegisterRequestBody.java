package ru.nikita.apicourse.api.request.body.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestBody {
    @NotEmpty
    private String firstName;
    @NotNull
    private String secondName;
    @Email
    private String email;
    @NotEmpty
    private String password;
}
