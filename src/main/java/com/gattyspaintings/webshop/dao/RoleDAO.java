package com.gattyspaintings.webshop.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

import com.gattyspaintings.webshop.entity.Role;

@AllArgsConstructor
@Component
public class RoleDAO {
    private final RoleRepository roleRepository;

    public Optional<Role> getById(String id) {
        return this.roleRepository.findById(id);
    }

    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    public long count(){
        return this.roleRepository.count();
    }
}
