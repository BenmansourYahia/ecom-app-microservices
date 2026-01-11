package net.yahia.chatbotai.tools;

import net.yahia.chatbotai.clients.ProductFeignClient;
import net.yahia.chatbotai.models.Product;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SearchProductsTool implements Function<SearchProductsTool.Request, SearchProductsTool.Response> {

    private final ProductFeignClient productClient;

    public SearchProductsTool(ProductFeignClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public Response apply(Request request) {
        try {
            if (request.productName() != null && !request.productName().isEmpty()) {
                // Search by name
                PagedModel<Product> products = productClient.searchProductsByName(request.productName());
                List<Product> productList = products.getContent().stream().toList();

                if (productList.isEmpty()) {
                    return new Response("Aucun produit trouvé avec le nom: " + request.productName(), null);
                }

                String result = formatProducts(productList);
                return new Response(result, productList);
            } else {
                // Get all products
                PagedModel<Product> products = productClient.getAllProducts();
                List<Product> productList = products.getContent().stream().toList();
                String result = formatProducts(productList);
                return new Response(result, productList);
            }
        } catch (Exception e) {
            return new Response("Erreur lors de la recherche de produits: " + e.getMessage(), null);
        }
    }

    private String formatProducts(List<Product> products) {
        if (products.isEmpty()) {
            return "Aucun produit disponible.";
        }

        StringBuilder sb = new StringBuilder("Produits disponibles:\n");
        for (Product product : products) {
            sb.append(String.format("- %s (ID: %d) - Prix: %.2f€ - Stock: %d unités\n",
                    product.getName(),
                    product.getId(),
                    product.getPrice(),
                    product.getQuantity()));
        }
        return sb.toString();
    }

    public record Request(String productName) {
    }

    public record Response(String message, List<Product> products) {
    }
}
