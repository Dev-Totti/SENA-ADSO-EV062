package com.tottidev.LotoUrban.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDataRegister(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotNull
        String name
) {
}
