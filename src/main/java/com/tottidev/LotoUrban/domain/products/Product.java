package com.tottidev.LotoUrban.domain.products;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String category;

    private Float price;

    private Boolean active;

    public Product(ProductDataRegister productDataRegister) {
        this.name = productDataRegister.name();
        this.category = productDataRegister.category();
        this.description = productDataRegister.description();
        this.price = productDataRegister.price();
        this.active = true;
    }

    public void update(ProductDataUpdate productDataUpdate) {
        if (productDataUpdate.name() != null) {
            this.name = productDataUpdate.name();
        }

        if (productDataUpdate.category() != null) {
            this.category = productDataUpdate.category();
        }

        if (productDataUpdate.description() != null) {
            this.description = productDataUpdate.description();
        }

        if (productDataUpdate.price() != null) {
            this.price = productDataUpdate.price();
        }

        if (productDataUpdate.active() != null) {
            this.active = productDataUpdate.active();
        }
    }
}
