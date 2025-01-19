package br.com.enterprise.orders.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String productId;
    private String name;
    private BigDecimal price;
    private int quantity;

}
