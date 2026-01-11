package net.yahia.chatbotai.clients;

import net.yahia.chatbotai.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface ProductFeignClient {

    @GetMapping("/products")
    PagedModel<Product> getAllProducts();

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable("id") Long id);

    @GetMapping("/products/search/byName?name={name}")
    PagedModel<Product> searchProductsByName(@PathVariable("name") String name);
}
