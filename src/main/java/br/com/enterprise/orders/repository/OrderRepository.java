package br.com.enterprise.orders.repository;

import br.com.enterprise.orders.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'orderId': ?0 }")
    Optional<Order> findByOrderId(String orderId);

    @Query("{ 'customerInfo.customerId': ?0 }")
    List<Order> findByCustomerInfoCustomerId(String customerId);

    @Query("{ 'orderDate': { $regex: '^?0' } }")
    List<Order> findByOrderDate(String datePrefix);
}
