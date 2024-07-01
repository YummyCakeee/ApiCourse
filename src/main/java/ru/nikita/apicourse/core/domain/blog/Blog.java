package ru.nikita.apicourse.core.domain.blog;

import jakarta.persistence.*;
import lombok.*;
import ru.nikita.apicourse.core.domain.blog.post.BlogPost;
import ru.nikita.apicourse.core.domain.user.User;
import ru.nikita.apicourse.spring.boot.data.domain.AbstractEntityAuditable;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "blogs")
public class Blog extends AbstractEntityAuditable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<BlogPost> blogPosts;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //TODO blog-user subscription table
}
