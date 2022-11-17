package com.mustache.ptbbs.controller;

import com.mustache.ptbbs.domain.user.dto.UserRequest;
import com.mustache.ptbbs.domain.user.dto.UserResponse;
import com.mustache.ptbbs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserResponse(id);
        log.info(userResponse.toString());
        return ResponseEntity
                .ok()
                .body(userResponse);
    }

    @PostMapping("{id}")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        return  ResponseEntity
                .ok()
                .body(userService.addUserRequest(userRequest));
    }
}
