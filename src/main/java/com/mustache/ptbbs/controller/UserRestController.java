package com.mustache.ptbbs.controller;

import com.mustache.ptbbs.domain.user.dto.UserRequest;
import com.mustache.ptbbs.domain.user.dto.UserResponse;
import com.mustache.ptbbs.domain.user.entity.User;
import com.mustache.ptbbs.service.UserServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserServise userServise;

    public UserRestController(UserServise userServise) {
        this.userServise = userServise;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse userResponse = userServise.getUserResponse(id);
        log.info(userResponse.toString());
        return ResponseEntity
                .ok()
                .body(userResponse);
    }

}
