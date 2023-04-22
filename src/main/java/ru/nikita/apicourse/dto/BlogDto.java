package ru.nikita.apicourse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BlogDto {
    private Long id;
    private Long userId;
    private String name;
    private String description;
}
