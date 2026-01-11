package net.yahia.kafkaspringcloudstream.consumers;

import net.yahia.kafkaspringcloudstream.events.CustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class CustomerEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(CustomerEventConsumer.class);

    @Bean
    public Consumer<CustomerEvent> customerConsumer() {
        return event -> {
            log.info("👤 Received Customer Event:");
            log.info("   Customer: {} (ID: {})", event.getCustomerName(), event.getCustomerId());
            log.info("   Email: {}", event.getEmail());
            log.info("   Event Type: {}", event.getEventType());
            log.info("   Timestamp: {}", event.getTimestamp());

            // Process customer event
            processCustomerEvent(event);
        };
    }

    private void processCustomerEvent(CustomerEvent event) {
        switch (event.getEventType()) {
            case REGISTERED:
                log.info("🎉 New customer registered - sending welcome email to {}", event.getEmail());
                break;
            case UPDATED:
                log.info("✏️  Customer profile updated for {}", event.getCustomerName());
                break;
            case DELETED:
                log.info("🗑️  Customer account deleted: {}", event.getCustomerName());
                break;
            case ORDER_PLACED:
                log.info("🛒 Customer {} placed an order", event.getCustomerName());
                break;
        }
    }
}
