package br.com.enterprise.orders.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Indexed(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "ID must not contain special characters")
    private String orderId;

    private Customer customerInfo;

    @Indexed
    @NotNull(message = "Order date is mandatory")
    @PastOrPresent(message = "Order date cannot be in the future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;

    private LocalDateTime processedOrderDate;

    private List<Product> products;
    private BigDecimal totalAmount;

}
