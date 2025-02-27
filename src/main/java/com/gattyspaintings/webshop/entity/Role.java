package com.gattyspaintings.webshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Role {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @JsonIgnore
    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role(String id) {
        this.id = id;
    }

    public Role(String id, String description) {
        this.id = id;
        this.description = description;
    }
}
