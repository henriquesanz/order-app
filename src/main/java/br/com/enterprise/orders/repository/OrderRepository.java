package br.com.enterprise.orders.repository;

import br.com.enterprise.orders.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    // Métodos personalizados, se necessário
}
