package com.gattyspaintings.webshop.dao;

import com.gattyspaintings.webshop.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserDAO {
    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public long count() {
        return userRepository.count();
    }
}
