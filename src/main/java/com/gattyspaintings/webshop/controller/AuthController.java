package com.gattyspaintings.webshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

import com.gattyspaintings.webshop.entity.User;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {

    }
}
