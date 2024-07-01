package ru.nikita.apicourse.core.domain.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.spring.boot.data.domain.AbstractEntityAuditable;

@Entity
@Table(name = "user_auth_identities")
@Getter
@Setter
public class UserAuthIdentity extends AbstractEntityAuditable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type")
    private AuthStrategyType type;

    @Column(name = "identity")
    private String identity;
}
