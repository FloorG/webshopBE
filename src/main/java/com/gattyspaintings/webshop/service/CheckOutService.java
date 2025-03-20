package com.gattyspaintings.webshop.service;

import com.gattyspaintings.webshop.dao.OrderDetailsDAO;
import com.gattyspaintings.webshop.dao.OrderItemsDAO;
import com.gattyspaintings.webshop.dao.ProductDAO;
import com.gattyspaintings.webshop.entity.OrderDetails;
import com.gattyspaintings.webshop.entity.OrderItem;
import com.gattyspaintings.webshop.entity.Product;
import com.gattyspaintings.webshop.entity.User;
import com.gattyspaintings.webshop.models.Cart;
import com.gattyspaintings.webshop.models.CheckOutRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckOutService {
    private final ProductDAO productDAO;
    private final OrderDetailsDAO orderDetailsDAO;
    private final OrderItemsDAO orderItemsDAO;

    public CheckOutService(ProductDAO productDAO, OrderDetailsDAO orderDetailsDAO, OrderItemsDAO orderItemsDAO) {
        this.productDAO = productDAO;
        this.orderDetailsDAO = orderDetailsDAO;
        this.orderItemsDAO = orderItemsDAO;
    }

    public String createOrder(CheckOutRequest checkOutData) {
        OrderDetails orderToCreate = new OrderDetails(
                checkOutData.getFirstName(),
                checkOutData.getLastName(),
                checkOutData.getEmail(),
                checkOutData.getAddress(),
                checkOutData.getCity(),
                checkOutData.getZipCode(),
                checkOutData.getHouseNumber(),
                calculateTotal(checkOutData.getItems())

        );
        OrderDetails createdOrder = orderDetailsDAO.save(orderToCreate);

        List<OrderItem> items = checkOutData.getItems().stream().map(e -> {
            Product product = productDAO.getById(e.getId());
            return new OrderItem(
                    product,
                    createdOrder,
                    e.getQuantity(),
                    product.getPrice()
            );
        }).toList();

        orderItemsDAO.saveAll(items);

        return createdOrder.getId();
    }

    private BigDecimal calculateTotal(List<Cart> items) {
        BigDecimal total = new BigDecimal(0);
        for (Cart item : items) {
            total = total.add(
                    productDAO.getById(item.getId()).getPrice().multiply(item.getQuantity())
            );
        }
        return total;
    }
}
