package com.gattyspaintings.webshop.dao;

import com.gattyspaintings.webshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
