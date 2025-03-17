package com.gattyspaintings.webshop.dao;

import com.gattyspaintings.webshop.Exception.ResourceNotFoundException;
import com.gattyspaintings.webshop.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CategoryDAO {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category getById(String id) {
        return this.categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void delete(String id) {
        this.categoryRepository.deleteById(id);
    }

    public long count() {
        return this.categoryRepository.count();
    }
}
