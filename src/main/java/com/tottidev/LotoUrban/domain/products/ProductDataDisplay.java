package com.tottidev.LotoUrban.domain.products;

public record ProductDataDisplay(
        Long id,
        String name,
        String description,
        String category,
        Float price
) {

    public ProductDataDisplay(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getCategory(), product.getPrice());
    }
}
