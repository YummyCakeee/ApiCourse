package ru.nikita.apicourse.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataCollectionResponse extends BasicResponse {
    private List<?> data;
    private Integer count;
}
