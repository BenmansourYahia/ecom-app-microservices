package net.yahia.chatbotai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id; // Changed from Long to String
    private String name;
    private Double price;
    private Integer quantity;
}
