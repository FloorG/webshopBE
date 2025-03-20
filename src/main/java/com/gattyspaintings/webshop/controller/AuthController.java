package com.gattyspaintings.webshop.controller;

import com.gattyspaintings.webshop.Exception.*;
import com.gattyspaintings.webshop.config.JwtTokenUtil;
import com.gattyspaintings.webshop.dao.RoleDAO;
import com.gattyspaintings.webshop.dao.RoleRepository;
import com.gattyspaintings.webshop.dao.UserDAO;
import com.gattyspaintings.webshop.entity.Role;
import com.gattyspaintings.webshop.entity.User;
import com.gattyspaintings.webshop.models.JwtResult;
import com.gattyspaintings.webshop.models.LoginCredentials;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.passay.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final JwtTokenUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final RoleRepository roleRepository;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody @Valid User user) {
        if(!this.isValidPassword(user.getPassword())) {
            throw new UnmetPasswordRequirementsException();
        }

        Optional<User> requestedUser = userDAO.findByEmail(user.getEmail());
        if (requestedUser.isPresent()) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        Role standardUserRole = roleDAO.getById("USER").orElseThrow(StandardRoleNotFoundException::new);
        user.setRole(standardUserRole);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User newUser = userDAO.save(user);
        String token = jwtUtil.generateToken(newUser);

        return Collections.singletonMap("token", token);
    }

    @PostMapping("/login")
    public JwtResult login(@RequestBody @Valid LoginCredentials body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.email, body.password);
            authManager.authenticate(authInputToken);
            User requestedUser = userDAO.findByEmail(body.email).orElseThrow(ResourceNotFoundException::new);

            String token = jwtUtil.generateToken(requestedUser);
            JwtResult jwtResult = new JwtResult();

            jwtResult.token = token;

            return jwtResult;
        } catch (AuthenticationException authExc) {
            throw new ForbiddenException("Invalid credentials");
        }
    }

    private boolean isValidPassword(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(10, 25),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Special, 1)
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }

    @GetMapping("/myAccount")
    @PreAuthorize("hasRole('USER')")
    public User getMe(@AuthenticationPrincipal String email) {
        return userDAO.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }
}
