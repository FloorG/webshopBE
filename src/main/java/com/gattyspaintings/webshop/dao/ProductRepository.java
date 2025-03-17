package com.gattyspaintings.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gattyspaintings.webshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {}
