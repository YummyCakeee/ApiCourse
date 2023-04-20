package ru.nikita.apicourse.dao;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BlogDao {
    private Long id;
    private Long userId;
    private String name;
    private String description;
}
