package net.yahia.kafkaspringcloudstream.consumers;

import net.yahia.kafkaspringcloudstream.events.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class OrderEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    @Bean
    public Consumer<OrderEvent> orderConsumer() {
        return event -> {
            log.info("📦 Received Order Event:");
            log.info("   Order ID: {}", event.getOrderId());
            log.info("   Customer: {} (ID: {})", event.getCustomerName(), event.getCustomerId());
            log.info("   Total Amount: {}€", event.getTotalAmount());
            log.info("   Status: {}", event.getStatus());
            log.info("   Timestamp: {}", event.getTimestamp());

            // Process order logic here
            processOrder(event);
        };
    }

    private void processOrder(OrderEvent event) {
        switch (event.getStatus()) {
            case CREATED:
                log.info("✅ New order created - sending confirmation email");
                break;
            case CONFIRMED:
                log.info("✅ Order confirmed - preparing for shipment");
                break;
            case SHIPPED:
                log.info("🚚 Order shipped - notifying customer");
                break;
            case DELIVERED:
                log.info("✅ Order delivered - requesting feedback");
                break;
            case CANCELLED:
                log.info("❌ Order cancelled - processing refund");
                break;
        }
    }
}
