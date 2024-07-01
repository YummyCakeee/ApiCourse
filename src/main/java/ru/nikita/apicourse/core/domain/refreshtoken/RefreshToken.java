package ru.nikita.apicourse.core.domain.refreshtoken;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.spring.boot.data.domain.AbstractEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends AbstractEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token_type")
    private AuthStrategyType type;

    @Column(name = "token")
    String token;

    @Column(name = "expiry_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiryDate;
}
