package com.gattyspaintings.webshop.models;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @NotBlank
    private String id;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Digits(integer = 2, fraction = 0)
    private BigDecimal quantity;
}
