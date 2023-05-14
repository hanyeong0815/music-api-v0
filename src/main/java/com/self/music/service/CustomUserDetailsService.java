package com.self.music.service;

import com.self.music.domain.Users;
import com.self.music.domain.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users users = usersRepo.findByUsername(username).orElseThrow();
            return users;
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("해당하는 유저를 찾지 못하였습니다.");
        }
    }
}
