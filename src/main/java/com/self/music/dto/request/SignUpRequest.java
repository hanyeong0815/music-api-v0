package com.self.music.dto.request;

import com.self.music.domain.Users;
import com.self.music.domain.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class SignUpRequest {
    @Getter
    @AllArgsConstructor
    public static class SignUpReq {
        private String userName;
        private String password;
        private String email;
        private String name;
        private String nickname;
        private GenderType gender;
        private List<String> roles;
    }

}
