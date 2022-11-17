package com.mustache.ptbbs.service;

import com.mustache.ptbbs.domain.user.dto.UserRequest;
import com.mustache.ptbbs.domain.user.dto.UserResponse;
import com.mustache.ptbbs.domain.user.entity.User;
import com.mustache.ptbbs.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository); // 독립적인 테스트를 보장하기 위해서 수동으로 DI 해주기
    }

    @Test
    @DisplayName("addUserRequest 가 정상적으로 회원 등록 메세지를 출력하는지 Test")
    void addUserRequest() {

        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1l, "test", "11223344"));

        UserResponse userResponse = userService.addUserRequest(new UserRequest("test", "11223344"));
        assertEquals("test", userResponse.getUsername());
        assertEquals("유저가 추가되었습니다.", userResponse.getMessage());
    }
}