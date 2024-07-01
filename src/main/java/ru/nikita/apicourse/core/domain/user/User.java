package ru.nikita.apicourse.core.domain.user;

import jakarta.persistence.*;
import lombok.*;
import ru.nikita.apicourse.core.domain.acl.enumeration.ACRoleScope;
import ru.nikita.apicourse.core.domain.acl.role.entry.EntryRoleTarget;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.core.domain.user.password.UserPassword;
import ru.nikita.apicourse.spring.boot.data.domain.AbstractEntityAuditable;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User extends AbstractEntityAuditable implements EntryRoleTarget {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;

    @OneToOne(mappedBy = "user")
    private UserPassword password;

    @Override
    public ACRoleScope getEntryRoleTargetScope() {
        return null;
    }

    @Override
    public Long getEntryRoleTargetId() {
        return null;
    }

    @Override
    public EntryRoleTarget getChainRoleTarget() {
        return null;
    }
}
