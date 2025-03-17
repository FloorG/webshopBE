package com.gattyspaintings.webshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderItem {
    @Id
    @UuidGenerator
    private String id;

    private BigDecimal purchasePrice;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Digits(integer = 2, fraction = 0)
    private BigDecimal quantity;

    @ManyToOne
    @JsonIgnore
    private OrderDetails orderDetails;

    @ManyToOne
    private Product product;

    public OrderItem(Product product, OrderDetails orderDetails, BigDecimal quantity, BigDecimal purchasePrice) {
        this.product = product;
        this.orderDetails = orderDetails;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }
}
