package ru.nikita.apicourse.dto;

import lombok.*;

import java.time.LocalDateTime;

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
