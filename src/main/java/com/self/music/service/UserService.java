package com.self.music.service;

import com.self.music.core.error.ErrorCode;
import com.self.music.core.error.Preconditions;
import com.self.music.core.error.member.MemberErrorCode;
import com.self.music.domain.Users;
import com.self.music.domain.UsersRepo;
import com.self.music.dto.request.ChangePwDto.ChangePwRequest;
import com.self.music.dto.request.ChangePwDto.HasPwRequest;
import com.self.music.dto.request.CheckPwDto.CheckPwRequest;
import com.self.music.dto.response.UsersResponse.UsersRes;
import com.self.music.utills.PasswordEncoderStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UsersRepo usersRepo;
    private final PasswordEncoderStorage passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users users = usersRepo.findByUsername(username).orElseThrow();
            return users;
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("해당하는 유저를 찾지 못하였습니다.");
        }
    }

    public boolean signUp(Users users) {
        Users hasUser = usersRepo.findUserNameByUsername(users.getUsername());
        if (null != hasUser) {
            return false;
        }
        Users newUser = usersRepo.save(users);
        return newUser != null;
    }

    public UsersRes usersInfo(String username) {
        Users users = usersRepo.findByUsername(username).get();
        UsersRes res = UsersRes.toResFrom(users);
        return res;
    }

    public boolean deleteUser(Long userId) {
        Preconditions.validate(
                usersRepo.findById(userId).isPresent(),
                MemberErrorCode.NO_SUCH_USER
        );

        usersRepo.deleteById(userId);
        return true;
    }

    public String findIdFiltering(String name, String email) {
        String username = usersRepo.findByNameAndEmail(name, email).getUsername();
        String filteringUsername = username.replace(username.substring(1, 3), "**");
        return filteringUsername;
    }

    public boolean hasUserInfoByUserInfo(HasPwRequest request) {
        Users users = usersRepo.findByIdAndNameAndEmail(request.getUserId(), request.getName(), request.getEmail());
        return users != null;
    }

    public boolean changePw(ChangePwRequest req) {
        if (null != req.getOriginalPw()) {
            boolean chkPw = this.checkPw(new CheckPwRequest(req.getUserId(), req.getOriginalPw()));
            if (!chkPw) {
                return false;
            }
        }
        boolean changedPw = usersRepo.changePw(req.getUserId(), req.getChangedPw()) > 0;
        return changedPw;
    }

    public List<Users> testFindAllUsers() {
        return usersRepo.findAll();
    }

    public boolean checkPw(CheckPwRequest req) {
        Users users = usersRepo.findById(req.getUserId()).get();
        if (null == users) {
            return false;
        }
        boolean matchedPw = passwordEncoder.getPasswordEncoder().matches(req.getPassword(), users.getPassword());
        return matchedPw;
    }
}
