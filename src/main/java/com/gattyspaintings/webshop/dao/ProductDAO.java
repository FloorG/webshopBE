package com.gattyspaintings.webshop.dao;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import java.util.List;

import com.gattyspaintings.webshop.entity.Product;
import com.gattyspaintings.webshop.Exception.ResourceNotFoundException;

@AllArgsConstructor
@Component
public class ProductDAO {

    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product getById(String id) {
        return this.productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }
}
