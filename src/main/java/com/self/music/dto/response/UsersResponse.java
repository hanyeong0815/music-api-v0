package com.self.music.dto.response;

import com.self.music.domain.Users;
import com.self.music.domain.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UsersResponse {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UsersRes {
        private String userId;
        private String nickname;
        private GenderType gender;
        private String email;

        public static UsersRes toResFrom(Users users) {
            return new UsersRes(users.getUsername(), users.getNickname(), users.getGenderType(), users.getEmail());
        }
    }
}
