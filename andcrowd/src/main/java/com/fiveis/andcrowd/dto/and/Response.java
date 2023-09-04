package com.fiveis.andcrowd.dto.and;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result;

    // 성공 리턴
    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }
}