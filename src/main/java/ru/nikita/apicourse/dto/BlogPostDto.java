package ru.nikita.apicourse.dto;

import lombok.*;
import ru.nikita.apicourse.models.Blog;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BlogPostDto {
    private Long id;
    private Long blogId;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime editDate;
}
