package com.gattyspaintings.webshop.controller;

import com.gattyspaintings.webshop.Exception.ResourceNotFoundException;
import com.gattyspaintings.webshop.dao.OrderDetailsDAO;
import com.gattyspaintings.webshop.dao.UserDAO;
import com.gattyspaintings.webshop.entity.OrderDetails;
import com.gattyspaintings.webshop.entity.User;
import com.gattyspaintings.webshop.models.CheckOutRequest;
import com.gattyspaintings.webshop.service.CheckOutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckOutController {
    private final CheckOutService checkOutService;
    private final UserDAO userDAO;
    private final OrderDetailsDAO orderDetailsDAO;

    public CheckOutController(CheckOutService checkOutService, UserDAO userDAO, OrderDetailsDAO orderDetailsDAO) {
        this.userDAO = userDAO;
        this.checkOutService = checkOutService;
        this.orderDetailsDAO = orderDetailsDAO;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrderDetails> checkOut(@AuthenticationPrincipal String email, @RequestBody @Valid CheckOutRequest checkOutData) {
        User user = userDAO.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        String createdOrderId = checkOutService.createOrder(checkOutData, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsDAO.getById(createdOrderId));
    }
}
