package br.com.enterprise.orders.service;

import br.com.enterprise.orders.client.ExternalServiceBClient;
import br.com.enterprise.orders.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderNotificationService {

    @Autowired
    private final ExternalServiceBClient externalServiceClient;

    public OrderNotificationService(ExternalServiceBClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    @Async
    public void sendOrderNotification(Order order) {
        try {
            log.info("Sending order notification for Order ID: {}", order.getOrderId());

            externalServiceClient.postData(order);

            log.info("Notification sent successfully for Order ID: {}", order.getOrderId());
        } catch (Exception e) {
            log.error("Failed to send notification for Order ID: {}", order.getOrderId());
        }
    }

}
