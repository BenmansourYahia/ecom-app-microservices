package net.yahia.kafkaspringcloudstream.producers;

import net.yahia.kafkaspringcloudstream.events.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class OrderEventProducer {

    private OrderEvent lastOrderEvent;

    @Bean
    public Supplier<Message<OrderEvent>> orderProducer() {
        return () -> {
            if (lastOrderEvent != null) {
                Message<OrderEvent> message = MessageBuilder
                        .withPayload(lastOrderEvent)
                        .build();
                lastOrderEvent = null; // Reset after sending
                return message;
            }
            return null;
        };
    }

    public void publishOrderEvent(OrderEvent event) {
        this.lastOrderEvent = event;
    }
}
