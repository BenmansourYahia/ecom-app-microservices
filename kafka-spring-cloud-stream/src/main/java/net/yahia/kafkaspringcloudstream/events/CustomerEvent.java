package net.yahia.kafkaspringcloudstream.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvent {
    private Long customerId;
    private String customerName;
    private String email;
    private CustomerEventType eventType;
    private LocalDateTime timestamp;

    public enum CustomerEventType {
        REGISTERED,
        UPDATED,
        DELETED,
        ORDER_PLACED
    }

    public CustomerEvent(Long customerId, String customerName, String email, CustomerEventType eventType) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.eventType = eventType;
        this.timestamp = LocalDateTime.now();
    }
}
