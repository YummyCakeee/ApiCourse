package ru.nikita.apicourse.api.request.body.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestBody {
    @NotNull
    private AuthStrategyType strategy;
    @NotBlank
    private String refreshToken;

}
