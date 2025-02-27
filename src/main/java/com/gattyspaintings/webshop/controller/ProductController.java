package com.gattyspaintings.webshop.controller;

import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.List;

import com.gattyspaintings.webshop.dao.ProductDAO;
import com.gattyspaintings.webshop.entity.Product;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductDAO productDAO;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productDAO.getById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productDAO.save(product);
    }
}
