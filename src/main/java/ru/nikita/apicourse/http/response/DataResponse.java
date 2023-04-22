package ru.nikita.apicourse.http.response;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse extends BasicResponse{
    private Object data;
}
