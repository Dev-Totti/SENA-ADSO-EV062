package com.tottidev.LotoUrban.domain.products;

public record ProductDataResponse(
        Long id,
        String name,
        String description,
        String category,
        Float price
) {
    public ProductDataResponse(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getCategory(), product.getPrice());
    }
}
