package com.mustache.ptbbs.service;

import com.mustache.ptbbs.domain.user.dto.UserRequest;
import com.mustache.ptbbs.domain.user.dto.UserResponse;
import com.mustache.ptbbs.domain.user.entity.User;
import com.mustache.ptbbs.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
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

    public UserResponse addUserRequest(UserRequest userRequest) {
        User user = userRequest.toEntity(); // Req -> Entity

        // 중복 이름이 있는지 찾는 로직
        Optional<User> optUser =  userRepository.findByUsername(userRequest.getUsername());
        if (optUser.isEmpty()) {
            User savedUser = userRepository.save(user);
            return new UserResponse(savedUser.getId(), savedUser.getUsername(), "유저가 추가되었습니다.");
        } else {
            return new UserResponse(null , userRequest.getUsername(), "해당 UserName은 이미 존재합니다.");
        }
    }
}
