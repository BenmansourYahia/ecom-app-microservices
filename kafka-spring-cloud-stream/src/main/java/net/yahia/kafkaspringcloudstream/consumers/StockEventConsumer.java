package net.yahia.kafkaspringcloudstream.consumers;

import net.yahia.kafkaspringcloudstream.events.ProductStockEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class StockEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(StockEventConsumer.class);
    private static final int LOW_STOCK_THRESHOLD = 10;

    @Bean
    public Consumer<ProductStockEvent> stockConsumer() {
        return event -> {
            log.info("📊 Received Stock Event:");
            log.info("   Product: {} (ID: {})", event.getProductName(), event.getProductId());
            log.info("   Operation: {}", event.getOperation());
            log.info("   Previous Quantity: {}", event.getPreviousQuantity());
            log.info("   New Quantity: {}", event.getQuantity());
            log.info("   Timestamp: {}", event.getTimestamp());

            // Check for low stock
            if (event.getQuantity() < LOW_STOCK_THRESHOLD) {
                log.warn("⚠️  LOW STOCK ALERT: {} has only {} units remaining!",
                        event.getProductName(), event.getQuantity());
            }

            // Process stock update
            processStockUpdate(event);
        };
    }

    private void processStockUpdate(ProductStockEvent event) {
        switch (event.getOperation()) {
            case ADD:
                log.info("➕ Stock added for {}", event.getProductName());
                break;
            case REMOVE:
                log.info("➖ Stock removed for {}", event.getProductName());
                break;
            case UPDATE:
                log.info("🔄 Stock updated for {}", event.getProductName());
                break;
            case RESTOCK:
                log.info("📦 Product restocked: {}", event.getProductName());
                break;
        }
    }
}
