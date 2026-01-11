package net.yahia.kafkaspringcloudstream.controllers;

import net.yahia.kafkaspringcloudstream.events.OrderEvent;
import net.yahia.kafkaspringcloudstream.events.ProductStockEvent;
import net.yahia.kafkaspringcloudstream.events.CustomerEvent;
import net.yahia.kafkaspringcloudstream.producers.OrderEventProducer;
import net.yahia.kafkaspringcloudstream.producers.StockEventProducer;
import net.yahia.kafkaspringcloudstream.producers.CustomerEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final OrderEventProducer orderProducer;
    private final StockEventProducer stockProducer;
    private final CustomerEventProducer customerProducer;

    public EventController(OrderEventProducer orderProducer,
            StockEventProducer stockProducer,
            CustomerEventProducer customerProducer) {
        this.orderProducer = orderProducer;
        this.stockProducer = stockProducer;
        this.customerProducer = customerProducer;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> publishOrderEvent(@RequestBody OrderEvent event) {
        orderProducer.publishOrderEvent(event);
        return ResponseEntity.ok("Order event published: " + event.getOrderId());
    }

    @PostMapping("/stock")
    public ResponseEntity<String> publishStockEvent(@RequestBody ProductStockEvent event) {
        stockProducer.publishStockEvent(event);
        return ResponseEntity.ok("Stock event published for product: " + event.getProductName());
    }

    @PostMapping("/customers")
    public ResponseEntity<String> publishCustomerEvent(@RequestBody CustomerEvent event) {
        customerProducer.publishCustomerEvent(event);
        return ResponseEntity.ok("Customer event published: " + event.getCustomerName());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Kafka Event Service is running");
    }
}
