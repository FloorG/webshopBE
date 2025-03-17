package com.gattyspaintings.webshop.dao;

import com.gattyspaintings.webshop.Exception.ResourceNotFoundException;
import com.gattyspaintings.webshop.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    public long count() {
        return this.productRepository.count();
    }
}
