package com.gattyspaintings.webshop.controller;

import com.gattyspaintings.webshop.Exception.ResourceNotFoundException;
import com.gattyspaintings.webshop.dao.OrderDetailsDAO;
import com.gattyspaintings.webshop.dao.UserDAO;
import com.gattyspaintings.webshop.entity.OrderDetails;
import com.gattyspaintings.webshop.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderDetailsDAO orderDetailsDAO;
    private final UserDAO userDAO;

    public OrderController(OrderDetailsDAO orderDetailsDAO, UserDAO userDAO) {
        this.orderDetailsDAO = orderDetailsDAO;
        this.userDAO = userDAO;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<OrderDetails> getOrders(@AuthenticationPrincipal String email) {
        User user = userDAO.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        return orderDetailsDAO.getByUser(user);
    }

    @GetMapping("/id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public OrderDetails getOrder(@AuthenticationPrincipal String email, @PathVariable String id) {
        User user = userDAO.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        return orderDetailsDAO.getByIdAndUser(id, user);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDetails> getAllOrders() {
        return orderDetailsDAO.getAll();
    }
}
