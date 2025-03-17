package com.gattyspaintings.webshop.controller;

import com.gattyspaintings.webshop.dao.ProductDAO;
import com.gattyspaintings.webshop.entity.Product;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductDAO productDAO;

    @GetMapping("/all")
    public List<Product> getAllProductsS() {
        return productDAO.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productDAO.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct(@RequestBody @Valid Product product) {
        return productDAO.save(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable String id) {
        productDAO.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        if (!id.equals(product.getId())) {
            throw new IllegalArgumentException("Product could not been found");
        }

        return productDAO.save(product);
    }
}
