package net.yahia.chatbotai.clients;

import net.yahia.chatbotai.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerFeignClient {

    @GetMapping("/customers")
    PagedModel<Customer> getAllCustomers();

    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable("id") Long id);

    @GetMapping("/customers/search/byEmail?email={email}")
    Customer getCustomerByEmail(@PathVariable("email") String email);
}
