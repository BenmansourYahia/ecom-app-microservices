package net.yahia.chatbotai.tools;

import net.yahia.chatbotai.clients.CustomerFeignClient;
import net.yahia.chatbotai.models.Customer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GetCustomerTool implements Function<GetCustomerTool.Request, GetCustomerTool.Response> {

    private final CustomerFeignClient customerClient;

    public GetCustomerTool(CustomerFeignClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public Response apply(Request request) {
        try {
            if (request.customerId() != null) {
                // Get by ID
                Customer customer = customerClient.getCustomerById(request.customerId());
                String result = formatCustomer(customer);
                return new Response(result, customer);
            } else if (request.email() != null && !request.email().isEmpty()) {
                // Get by email
                Customer customer = customerClient.getCustomerByEmail(request.email());
                String result = formatCustomer(customer);
                return new Response(result, customer);
            } else {
                return new Response("Veuillez fournir un ID client ou un email.", null);
            }
        } catch (Exception e) {
            return new Response("Erreur lors de la recherche du client: " + e.getMessage(), null);
        }
    }

    private String formatCustomer(Customer customer) {
        if (customer == null) {
            return "Client non trouvé.";
        }
        return String.format("Client trouvé:\n- ID: %d\n- Nom: %s\n- Email: %s",
                customer.getId(),
                customer.getName(),
                customer.getEmail());
    }

    public record Request(Long customerId, String email) {
    }

    public record Response(String message, Customer customer) {
    }
}
