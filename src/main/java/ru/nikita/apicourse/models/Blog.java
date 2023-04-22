package ru.nikita.apicourse.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "blogs")
public class Blog {
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
