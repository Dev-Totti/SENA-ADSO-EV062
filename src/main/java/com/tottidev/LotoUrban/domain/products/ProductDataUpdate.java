package com.tottidev.LotoUrban.domain.products;


public record ProductDataUpdate(
        String name,
        String category,
        String description,
        Float price,
        Boolean active
) {
}
