package br.com.enterprise.orders.controller;

import br.com.enterprise.orders.model.Order;
import br.com.enterprise.orders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "List all orders")
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "List all orders filtered by orders")
    @GetMapping("/filterByDate")
    public List<Order> getOrdersByDate(@RequestParam(name = "orderDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate orderDate) {
        return orderService.filterByDate(orderDate);
    }

    @Operation(summary = "List all orders filtered by customerId")
    @GetMapping("/filterByCustomerId")
    public List<Order> getOrdersByDate(@RequestParam(name = "customerId") String customerId) {
        return orderService.filterByCustomerId(customerId);
    }

    @Operation(summary = "Send a order")
    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}
