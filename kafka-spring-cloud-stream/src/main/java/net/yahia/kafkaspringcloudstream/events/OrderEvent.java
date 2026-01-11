package net.yahia.kafkaspringcloudstream.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private Long customerId;
    private String customerName;
    private Double totalAmount;
    private OrderStatus status;
    private LocalDateTime timestamp;

    public enum OrderStatus {
        CREATED,
        CONFIRMED,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    public OrderEvent(Long orderId, Long customerId, String customerName, Double totalAmount, OrderStatus status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
