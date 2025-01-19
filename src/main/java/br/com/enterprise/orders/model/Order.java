package br.com.enterprise.orders.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "orders")
@Data
public class Order {

    @Id
    private String id;
    private String customerId;
    private List<Product> products;
    private BigDecimal totalAmount;

}
