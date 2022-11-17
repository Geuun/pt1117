package com.mustache.ptbbs.service;

import com.mustache.ptbbs.domain.user.dto.UserRequest;
import com.mustache.ptbbs.domain.user.dto.UserResponse;
import com.mustache.ptbbs.domain.user.entity.User;
import com.mustache.ptbbs.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServise {

    private final UserRepository userRepository;

    public UserServise(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserResponse(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            return new UserResponse(id, "", "해당 id의 유저가 없습니다.");
        } else {
            User user = optUser.get();
            return new UserResponse(user.getId(), user.getUsername(), "");
        }
    }


}
