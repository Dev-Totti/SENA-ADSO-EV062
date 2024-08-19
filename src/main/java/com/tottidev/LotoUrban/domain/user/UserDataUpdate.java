package com.tottidev.LotoUrban.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDataUpdate(
        @NotNull
        Long id,
        @NotBlank
        String name
) {
}
