package com.mustache.ptbbs.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    // Request -> Entity
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
