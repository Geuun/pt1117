package com.mustache.ptbbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.ptbbs.domain.user.dto.UserRequest;
import com.mustache.ptbbs.domain.user.dto.UserResponse;
import com.mustache.ptbbs.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class) // 특정 클래스 테스트 가능하게
class UserRestControllerTest {

    @Autowired
    MockMvc mockMvc; // MVC 동작을 배포 전에 테스트할 수 있게 해줌

    @Autowired
    ObjectMapper objectMapper; // Json 직렬화 & 역직렬화 를 위해 사용

    @MockBean
    UserService userService; // 가짜 객체 생성

    @Test
    @DisplayName("GET getUserResponse: 아이디로 유저 조회 GET API Test")
    void getUserResponse() throws Exception {
        Long id = 1l;

        given(userService.getUserResponse(id))
                .willReturn(new UserResponse(1l, "geun", "조회 성공"));

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("geun"))
                .andExpect(jsonPath("$.message").value("조회 성공"))
                .andDo(print());
    }

    @Test
    @DisplayName("getUserResponse 조회 실패 테스트")
    void getUserResponseFail() throws Exception {
        given(userService.getUserResponse(2l))
                .willReturn(new UserResponse(null, "", "해당 id의 유저가 없습니다."));

        mockMvc.perform(get("/api/v1/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isEmpty())
                .andExpect(jsonPath("$.message").value("해당 id의 유저가 없습니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("POST addUserRequest: 유저 추가 POST API TEST")
    void addUserRequest() throws Exception {
        Long id = 1l;
        UserRequest userRequest = new UserRequest("test", "");
        UserResponse userResponse = new UserResponse(1l, userRequest.getUsername(), "회원 등록 성공");

        given(userService.addUserRequest(any()))
                .willReturn(userResponse);

        mockMvc.perform(post("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andDo(print());

        verify(userService).addUserRequest(userRequest);
    }
}