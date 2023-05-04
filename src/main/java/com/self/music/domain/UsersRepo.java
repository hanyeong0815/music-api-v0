package com.self.music.domain;

import com.self.music.domain.mapping.UserInfoMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Long> {
    @Query(
            "select u from Users u where u.username = ?1"
    )
    Users findUserNameByUsername(String username);

    Optional<Users> findByUsername(String username);

    UserInfoMapping findByNameAndEmail(String name, String email);

    Users findByIdAndNameAndEmail(Long userId, String name, String email);

    @Modifying
    @Transactional
    @Query(
            "update Users u set u.password = ?2 where u.id = ?1"
    )
    int changePw(Long userId, String changedPw);

    int deleteByUsername(String username);

    @Query(
            "select u.username from Users u where u.id = ?1"
    )
    String findUserNameById(Long userId);
}
