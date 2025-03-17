package com.gattyspaintings.webshop.dao;

import com.gattyspaintings.webshop.Exception.ResourceNotFoundException;
import com.gattyspaintings.webshop.entity.OrderDetails;
import com.gattyspaintings.webshop.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailsDAO {
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsDAO(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<OrderDetails> getAll() {
        return orderDetailsRepository.findAll();
    }

    public List<OrderDetails> getByUser(User user) {
        return orderDetailsRepository.getOrderDetailsByUser(user);
    }

    public OrderDetails getById(String id) {
        return orderDetailsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public OrderDetails getByIdAndUser(String id, User user) {
        return orderDetailsRepository.getOrderDetailsByIdAndUser(id, user).orElseThrow(ResourceNotFoundException::new);
    }

    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }
}

