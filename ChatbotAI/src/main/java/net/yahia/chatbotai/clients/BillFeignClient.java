package net.yahia.chatbotai.clients;

import net.yahia.chatbotai.models.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "billing-service")
public interface BillFeignClient {

    @GetMapping("/bills")
    PagedModel<Bill> getAllBills();

    @GetMapping("/bills/{id}")
    Bill getBillById(@PathVariable("id") Long id);

    @GetMapping("/bills/search/byCustomerId?customerId={customerId}")
    PagedModel<Bill> getBillsByCustomerId(@PathVariable("customerId") Long customerId);
}
