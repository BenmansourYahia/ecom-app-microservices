package net.yahia.chatbotai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Long id;
    private Date billingDate; // Changed from LocalDateTime to Date
    private Long customerId;
    private Customer customer;
    private List<ProductItem> productItems;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductItem {
        private Long id;
        private String productId; // Changed from Long to String
        private Product product;
        private Integer quantity;
        private Double unitPrice;
    }
}
