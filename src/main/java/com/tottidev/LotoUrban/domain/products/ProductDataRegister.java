package com.tottidev.LotoUrban.domain.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDataRegister(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        String category,

        @NotNull
        Float price
) {
}
