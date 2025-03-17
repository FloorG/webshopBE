package com.gattyspaintings.webshop.controller;

import com.gattyspaintings.webshop.dao.CategoryDAO;
import com.gattyspaintings.webshop.entity.Category;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryDAO categoryDao;

    @GetMapping("/all")
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable String id) {
        return categoryDao.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(@RequestBody @Valid Category category) {
        return categoryDao.save(category);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable String id) {
        categoryDao.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category updateCategory(@PathVariable String id, @Valid @RequestBody Category category) {
        if (!id.equals(category.getId())) {
            throw new IllegalArgumentException("Category could not be found");
        }

        return categoryDao.save(category);
    }
}
