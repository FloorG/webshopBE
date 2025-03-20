package com.gattyspaintings.webshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderDetails {
    @Id
    @UuidGenerator
    private String id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String zipCode;
    private String houseNumber;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Positive
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "orderDetails")
    private List<OrderItem> items;

    public OrderDetails(String firstName, String lastName, String email, String address, String city, String zipCode, String houseNumber, BigDecimal totalPrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.totalPrice = totalPrice;
    }
}
