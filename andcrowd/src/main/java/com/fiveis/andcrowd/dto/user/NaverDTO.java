package com.fiveis.andcrowd.dto.user;

import lombok.*;

public class NaverDTO {
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Params{
        String authorizationCode;
        String state;
    }
}
