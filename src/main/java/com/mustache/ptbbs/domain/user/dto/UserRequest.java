package com.mustache.ptbbs.domain.user.dto;

import com.mustache.ptbbs.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;

    // Dto -> Entity
    public User toEntity() {
        return new User(username, password);
    }
}
