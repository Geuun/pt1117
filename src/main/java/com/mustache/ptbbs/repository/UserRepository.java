package com.mustache.ptbbs.repository;

import com.mustache.ptbbs.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이름 중복 확인을 위해 기능 추가
    Optional<User> findByUsername(String userName);
}
