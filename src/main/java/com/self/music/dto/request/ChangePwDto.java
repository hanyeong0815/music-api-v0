package com.self.music.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ChangePwDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HasPwRequest {
        private Long userId;
        private String name;
        private String email;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChangePwRequest {
        private Long userId;
        private String originalPw;
        private String changedPw;

        public ChangePwRequest ChangePwRequest(PasswordEncoder encoder) {
            return new ChangePwRequest(this.userId, this.originalPw, encoder.encode(this.changedPw));
        }
    }
}
