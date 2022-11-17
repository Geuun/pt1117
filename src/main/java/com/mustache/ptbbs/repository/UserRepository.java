package com.mustache.ptbbs.repository;

import com.mustache.ptbbs.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String userName);
}
