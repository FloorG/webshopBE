package com.gattyspaintings.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gattyspaintings.webshop.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    public List<Product> findByAvailableTrue();
}
