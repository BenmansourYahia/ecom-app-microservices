package net.yahia.kafkaspringcloudstream.producers;

import net.yahia.kafkaspringcloudstream.events.CustomerEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CustomerEventProducer {

    private CustomerEvent lastCustomerEvent;

    @Bean
    public Supplier<Message<CustomerEvent>> customerProducer() {
        return () -> {
            if (lastCustomerEvent != null) {
                Message<CustomerEvent> message = MessageBuilder
                        .withPayload(lastCustomerEvent)
                        .build();
                lastCustomerEvent = null;
                return message;
            }
            return null;
        };
    }

    public void publishCustomerEvent(CustomerEvent event) {
        this.lastCustomerEvent = event;
    }
}
