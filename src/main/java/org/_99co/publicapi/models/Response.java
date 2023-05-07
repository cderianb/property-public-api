package org._99co.publicapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Response<T> {
    @JsonProperty("result")
    private Boolean result;

    @JsonProperty("data")
    private T data;

}
