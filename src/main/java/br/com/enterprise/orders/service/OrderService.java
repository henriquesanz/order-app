package br.com.enterprise.orders.service;

import br.com.enterprise.orders.exception.DuplicateOrderException;
import br.com.enterprise.orders.model.Order;
import br.com.enterprise.orders.model.Product;
import br.com.enterprise.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderNotificationService notificationService;

    public OrderService(OrderRepository orderRepository, OrderNotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> filterByDate(LocalDate localDateTime) {
        return orderRepository.findByOrderDate(localDateTime.toString());
    }

    public List<Order> filterByCustomerId(String customerId) {
        return orderRepository.findByCustomerInfoCustomerId(customerId);
    }

    public Order saveOrder(Order order) {
        Optional<Order> existingOrder = orderRepository.findByOrderId(order.getOrderId());
        if (existingOrder.isPresent()) {
            throw new DuplicateOrderException("Order with ID " + order.getOrderId() + " already exists.");
        }
        if (order.getTotalAmount() == null) {
            order.setTotalAmount(BigDecimal.ZERO);
        }
        for(Product product : order.getProducts()){
            order.setTotalAmount(
                    order.getTotalAmount().add(
                            (BigDecimal.valueOf(product.getQuantity()).multiply(product.getPrice()))));
        }
        order.setProcessedOrderDate(LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime());

        Order savedOrder = orderRepository.save(order);

        // Chamada assíncrona para envio para o envio ao External Service B
        notificationService.sendOrderNotification(savedOrder);

        // Order salvo na base
        return orderRepository.save(savedOrder);
    }


}
