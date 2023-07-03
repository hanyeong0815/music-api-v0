package com.self.music.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.self.music.exception.user.UserErrorCode;
import com.self.music.utills.token.CommonAuthenticationToken;
import com.self.music.authentication.token.JwtTokenProvider;
import com.self.music.utills.token.UserAuthenticationToken;
import com.self.music.domain.*;
import com.self.music.dto.request.ChangePwDto.ChangePwRequest;
import com.self.music.dto.request.ChangePwDto.HasPwRequest;
import com.self.music.dto.request.CheckPwDto.CheckPwRequest;
import com.self.music.dto.response.JwtResponse;
import com.self.music.dto.response.UsersResponse.UsersRes;
import com.self.music.utills.password.PasswordEncoderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.self.music.common.util.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UsersRepo usersRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoderFactory passwordEncoder;

    @Override
    public Authentication signUp(Users users) {
        Users hasUser = usersRepo.findUserNameByUsername(users.getUsername());
        if (null != hasUser) {
            return null;
        }
        Users newUser = usersRepo.save(users);
        Collection<? extends GrantedAuthority> roles = newUser.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return CommonAuthenticationToken.authenticated(UserAuthenticationToken.class, newUser.getUsername(), newUser.getPassword(), roles);
    }

    @Override
    public JwtResponse login(Authentication authentication) {

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public UsersRes usersInfo(String username) {
        Users users = usersRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));
        return UsersRes.toResFrom(users);
    }

    @Override
    public boolean deleteUser(Long userId) {
        validate(
                usersRepo.findById(userId).isPresent(),
                UserErrorCode.NO_SUCH_USER
        );

        usersRepo.deleteById(userId);
        return true;
    }

    @Override
    public String findIdFiltering(String name, String email) {
        String username = usersRepo.findByNameAndEmail(name, email).getUsername();
        return username.replace(username.substring(1, 3), "**");
    }

    @Override
    public boolean hasUserInfoByUserInfo(HasPwRequest request) {
        Users users = usersRepo.findByIdAndNameAndEmail(request.getUserId(), request.getName(), request.getEmail());
        return users != null;
    }

    @Override
    public boolean changePw(ChangePwRequest req) {
        if (null != req.getOriginalPw()) {
            boolean chkPw = this.checkPw(new CheckPwRequest(req.getUserId(), req.getOriginalPw()));
            if (!chkPw) {
                return false;
            }
        }
        return usersRepo.changePw(req.getUserId(), req.getChangedPw()) > 0;
    }

    @Override
    public boolean checkPw(CheckPwRequest req) {
        Users users = usersRepo.findById(req.getUserId()).orElseThrow();
        return passwordEncoder.defaultEncoder().matches(req.getPassword(), users.getPassword());
    }

    @Override
    public String findUserNameById(Long userId) {
        return usersRepo.findUserNameById(userId);
    }
}
