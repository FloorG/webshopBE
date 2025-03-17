package com.gattyspaintings.webshop.service;

import com.gattyspaintings.webshop.dao.UserDAO;
import com.gattyspaintings.webshop.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> requestedUser = userDAO.findByEmail(email);

        if (requestedUser.isEmpty()) {
            throw new UsernameNotFoundException("User with " + email + " not found");
        }

        User user = requestedUser.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getId())));
    }
}
