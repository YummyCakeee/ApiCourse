package ru.nikita.apicourse.core.domain.user.password;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.spring.boot.data.domain.AbstractEntityAuditable;

@Entity
@Table(name = "user_passwords")
@Getter
@Setter
public class UserPassword extends AbstractEntityAuditable {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "password_hash")
    private String passwordHash;
}
