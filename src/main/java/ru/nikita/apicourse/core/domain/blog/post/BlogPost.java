package ru.nikita.apicourse.core.domain.blog.post;

import jakarta.persistence.*;
import lombok.*;
import ru.nikita.apicourse.core.domain.blog.Blog;
import ru.nikita.apicourse.spring.boot.data.domain.AbstractEntityAuditable;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "blog_posts")
public class BlogPost extends AbstractEntityAuditable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    @Column(name = "content")
    private String content;

}
