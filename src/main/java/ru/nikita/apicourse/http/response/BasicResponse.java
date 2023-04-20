package ru.nikita.apicourse.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponse {
    protected boolean success;
    protected String message;

    public BasicResponse(boolean success) {
        this.success = success;
        message = "";
    }
}
