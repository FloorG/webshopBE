package com.gattyspaintings.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gattyspaintings.webshop.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
