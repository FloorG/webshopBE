package com.gattyspaintings.webshop.dao;

import com.gattyspaintings.webshop.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemsDAO {
    private final OrderItemRepository orderItemRepository;

    public OrderItemsDAO(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem save(OrderItem orderItems) {
        return orderItemRepository.save(orderItems);
    }

    public void saveAll(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }
}
