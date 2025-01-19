package br.com.enterprise.orders.service;

import br.com.enterprise.orders.model.Order;
import br.com.enterprise.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        // Adicione lógica para calcular o totalAmount, validar dados, etc.
        return orderRepository.save(order);
    }
}
