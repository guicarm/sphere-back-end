package br.com.fiap.apisphere.user.dto;

import java.time.LocalDateTime;

import br.com.fiap.apisphere.user.User;

public record UserRequest(
        String name,
        String bio,
        String email,
        String password) {
    public User toModel() {
        return User.builder()
                .name(name)
                .bio(bio)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
