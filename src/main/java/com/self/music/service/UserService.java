package com.self.music.service;

import com.self.music.domain.Users;
import com.self.music.dto.request.ChangePwDto.ChangePwRequest;
import com.self.music.dto.request.ChangePwDto.HasPwRequest;
import com.self.music.dto.request.CheckPwDto.CheckPwRequest;
import com.self.music.dto.response.JwtResponse;
import com.self.music.dto.response.UsersResponse.UsersRes;
import org.springframework.security.core.Authentication;

public interface UserService {
    Authentication signUp(Users users);
    JwtResponse login(Authentication authentication);
    UsersRes usersInfo(String username);
    boolean deleteUser(Long userId);
    String findIdFiltering(String name, String email);
    boolean hasUserInfoByUserInfo(HasPwRequest request);
    boolean changePw(ChangePwRequest req);
    boolean checkPw(CheckPwRequest req);
    String findUserNameById(Long userId);
}
