package com.gattyspaintings.webshop.controller;

import com.gattyspaintings.webshop.dao.OrderDetailsDAO;
import com.gattyspaintings.webshop.entity.OrderDetails;
import com.gattyspaintings.webshop.models.CheckOutRequest;
import com.gattyspaintings.webshop.service.CheckOutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckOutController {
    private final CheckOutService checkOutService;
    private final OrderDetailsDAO orderDetailsDAO;

    public CheckOutController(CheckOutService checkOutService, OrderDetailsDAO orderDetailsDAO) {
        this.checkOutService = checkOutService;
        this.orderDetailsDAO = orderDetailsDAO;
    }

    @PostMapping
    public ResponseEntity<OrderDetails> checkOut(@RequestBody @Valid CheckOutRequest checkOutData) {
        String createdOrderId = checkOutService.createOrder(checkOutData);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsDAO.getById(createdOrderId));
    }
}
