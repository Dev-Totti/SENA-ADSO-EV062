package com.tottidev.LotoUrban.domain.user;

public record UserDataResponse(
        Long id,
        String email,
        String username,
        String name
) {
    public UserDataResponse(User user) {
        this(user.getId(), user.getEmail(), user.getUsername(), user.getName());
    }
}
