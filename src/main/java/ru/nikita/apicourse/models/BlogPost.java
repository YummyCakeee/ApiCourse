package ru.nikita.apicourse.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

    @Column(name = "edit_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime editDate;

    @PrePersist
    private void onSave() {
        final LocalDateTime date = LocalDateTime.now();
        createDate = date;
        editDate = date;
    }

    @PreUpdate
    private void onUpdate() {
        editDate = LocalDateTime.now();
    }

}
