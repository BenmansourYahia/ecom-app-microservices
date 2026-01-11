package net.yahia.kafkaspringcloudstream.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockEvent {
    private String productId;
    private String productName;
    private Integer quantity;
    private Integer previousQuantity;
    private StockOperation operation;
    private LocalDateTime timestamp;

    public enum StockOperation {
        ADD,
        REMOVE,
        UPDATE,
        RESTOCK
    }

    public ProductStockEvent(String productId, String productName, Integer quantity, Integer previousQuantity,
            StockOperation operation) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.previousQuantity = previousQuantity;
        this.operation = operation;
        this.timestamp = LocalDateTime.now();
    }
}
