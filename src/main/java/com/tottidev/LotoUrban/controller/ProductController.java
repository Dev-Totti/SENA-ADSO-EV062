package com.tottidev.LotoUrban.controller;

import com.tottidev.LotoUrban.domain.products.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDataRegister productDataRegister, UriComponentsBuilder uriComponentsBuilder) {
        Product product = productRepository.save(new Product(productDataRegister));
        ProductDataResponse productDataResponse = new ProductDataResponse(product);
        URI url = uriComponentsBuilder.path("/course/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(url).body(productDataResponse);

    }

    @GetMapping
    public ResponseEntity<Page<ProductDataDisplay>> getProducts(Pageable pageable) {
        System.out.println("getProducts");
        return ResponseEntity.ok(productRepository.findByActiveTrue(pageable).map(ProductDataDisplay::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductDataUpdate productDataUpdate, @PathVariable Long id) {
        Product product = productRepository.getReferenceById(id);
        product.update(productDataUpdate);
        return ResponseEntity.ok(new ProductDataDisplay(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.getReferenceById(id);
        product.setActive(false);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDataDisplay> getProduct(@PathVariable Long id) {
        Product product = productRepository.getReferenceById(id);
        return ResponseEntity.ok(new ProductDataDisplay(product));
    }

}
