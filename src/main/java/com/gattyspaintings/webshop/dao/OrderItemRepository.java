package com.gattyspaintings.webshop.dao;

import com.gattyspaintings.webshop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}
