package net.yahia.kafkaspringcloudstream.producers;

import net.yahia.kafkaspringcloudstream.events.ProductStockEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class StockEventProducer {

    private ProductStockEvent lastStockEvent;

    @Bean
    public Supplier<Message<ProductStockEvent>> stockProducer() {
        return () -> {
            if (lastStockEvent != null) {
                Message<ProductStockEvent> message = MessageBuilder
                        .withPayload(lastStockEvent)
                        .build();
                lastStockEvent = null;
                return message;
            }
            return null;
        };
    }

    public void publishStockEvent(ProductStockEvent event) {
        this.lastStockEvent = event;
    }
}
