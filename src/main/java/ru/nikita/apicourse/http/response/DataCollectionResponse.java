package ru.nikita.apicourse.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class DataCollectionResponse extends BasicResponse {
    private List<?> data;
    private Integer count;
    public DataCollectionResponse(List<?> data) {
        this.data = data;
        count = data.size();
    }

    public void setData(List<?> data) {
        this.data = data;
        count = data.size();
    }
}
