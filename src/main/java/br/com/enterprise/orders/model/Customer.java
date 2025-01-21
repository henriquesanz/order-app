package br.com.enterprise.orders.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Customer {

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "ID must not contain special characters")
    private String customerId;

    @NotBlank(message = "Customer name is mandatory")
    @Size(max = 100, message = "Customer name must not exceed 50 characters")
    private String customerName;

    @Pattern(regexp = "\\d+", message = "Phone number must contain only numbers")
    private String customerContact;

}
