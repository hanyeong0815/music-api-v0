package com.self.music.service;

import com.self.music.authentication.token.CommonAuthenticationToken;
import com.self.music.authentication.token.UserAuthenticationToken;
import com.self.music.core.error.Preconditions;
import com.self.music.core.error.member.MemberErrorCode;
import com.self.music.domain.Users;
import com.self.music.domain.UsersRepo;
import com.self.music.dto.request.ChangePwDto.ChangePwRequest;
import com.self.music.dto.request.ChangePwDto.HasPwRequest;
import com.self.music.dto.request.CheckPwDto.CheckPwRequest;
import com.self.music.dto.response.UsersResponse.UsersRes;
import com.self.music.utills.password.PasswordEncoderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserDetailsService, UserService {
    private final UsersRepo usersRepo;
    private final PasswordEncoderFactory passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users users = usersRepo.findByUsername(username).orElseThrow();
            return users;
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("해당하는 유저를 찾지 못하였습니다.");
        }
    }

    @Override
    public Authentication signUp(Users users) {
        Users hasUser = usersRepo.findUserNameByUsername(users.getUsername());
        if (null != hasUser) {
            return null;
        }
        Users newUser = usersRepo.save(users);
        if (newUser == null) {
            return null;
        }
        Collection<? extends GrantedAuthority> roles = newUser.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        Authentication auth = CommonAuthenticationToken.authenticated(UserAuthenticationToken.class, newUser.getUsername(), newUser.getPassword(), roles);
        return auth;
    }

    @Override
    public UsersRes usersInfo(String username) {
        Users users = usersRepo.findByUsername(username).get();
        return UsersRes.toResFrom(users);
    }

    @Override
    public boolean deleteUser(Long userId) {
        Preconditions.validate(
                usersRepo.findById(userId).isPresent(),
                MemberErrorCode.NO_SUCH_USER
        );

        usersRepo.deleteById(userId);
        return true;
    }

    @Override
    public String findIdFiltering(String name, String email) {
        String username = usersRepo.findByNameAndEmail(name, email).getUsername();
        String filteringUsername = username.replace(username.substring(1, 3), "**");
        return filteringUsername;
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
    public List<Users> testFindAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public boolean checkPw(CheckPwRequest req) {
        Users users = usersRepo.findById(req.getUserId()).get();
        boolean matchedPw = passwordEncoder.defaultEncoder().matches(req.getPassword(), users.getPassword());
        return matchedPw;
    }

    @Override
    public String findUserNameById(Long userId) {
        return usersRepo.findUserNameById(userId);
    }
}
