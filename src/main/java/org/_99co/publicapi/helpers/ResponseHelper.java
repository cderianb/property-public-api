package org._99co.publicapi.helpers;

import org._99co.publicapi.models.Response;

public class ResponseHelper {
    public static<T> Response<T> ok(T data){
        return Response.<T>builder()
                .result(true)
                .data(data)
                .build();
    }
}
