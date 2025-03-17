package com.gattyspaintings.webshop.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String description;

    @Column(nullable = false)
    @NotBlank
    private String slug;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;

    public Category(String name, String description, String slug, List<Product> products) {
        this.name = name;
        this.description = description;
        this.slug = slug;
        this.products = products;
    }
}
