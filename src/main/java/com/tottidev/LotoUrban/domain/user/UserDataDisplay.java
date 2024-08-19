package com.tottidev.LotoUrban.domain.user;

public record UserDataDisplay(
        Long id,
        String name,
        String email,
        String username
) {
    public UserDataDisplay(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getUsername());
    }
}
