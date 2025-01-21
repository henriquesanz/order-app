package br.com.enterprise.orders.model;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "ID must not contain special characters")
    private String productId;
    private String name;
    private BigDecimal price;
    private int quantity;

}
