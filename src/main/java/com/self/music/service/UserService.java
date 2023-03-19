package com.self.music.service;

import com.self.music.domain.Users;
import com.self.music.dto.request.ChangePwDto.ChangePwRequest;
import com.self.music.dto.request.ChangePwDto.HasPwRequest;
import com.self.music.dto.request.CheckPwDto.CheckPwRequest;
import com.self.music.dto.response.UsersResponse.UsersRes;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    Authentication signUp(Users users);
    UsersRes usersInfo(String username);
    boolean deleteUser(Long userId);
    String findIdFiltering(String name, String email);
    boolean hasUserInfoByUserInfo(HasPwRequest request);
    boolean changePw(ChangePwRequest req);
    List<Users> testFindAllUsers();
    boolean checkPw(CheckPwRequest req);

    String findUserNameById(Long userId);
}
