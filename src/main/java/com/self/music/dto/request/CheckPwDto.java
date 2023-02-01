package com.self.music.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CheckPwDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CheckPwRequest {
        private Long userId;
        private String password;
    }
}
