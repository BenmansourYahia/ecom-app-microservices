package net.yahia.chatbotai.tools;

import net.yahia.chatbotai.clients.BillFeignClient;
import net.yahia.chatbotai.models.Bill;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;

@Component
public class GetBillsTool implements Function<GetBillsTool.Request, GetBillsTool.Response> {

    private final BillFeignClient billClient;

    public GetBillsTool(BillFeignClient billClient) {
        this.billClient = billClient;
    }

    @Override
    public Response apply(Request request) {
        try {
            if (request.customerId() != null) {
                // Get bills by customer ID
                PagedModel<Bill> bills = billClient.getBillsByCustomerId(request.customerId());
                List<Bill> billList = bills.getContent().stream().toList();
                String result = formatBills(billList, request.customerId());
                return new Response(result, billList);
            } else if (request.billId() != null) {
                // Get specific bill
                Bill bill = billClient.getBillById(request.billId());
                String result = formatBill(bill);
                return new Response(result, List.of(bill));
            } else {
                return new Response("Veuillez fournir un ID de facture ou un ID client.", null);
            }
        } catch (Exception e) {
            return new Response("Erreur lors de la recherche des factures: " + e.getMessage(), null);
        }
    }

    private String formatBills(List<Bill> bills, Long customerId) {
        if (bills.isEmpty()) {
            return "Aucune facture trouvée pour le client ID: " + customerId;
        }

        StringBuilder sb = new StringBuilder(String.format("Factures du client %d:\n", customerId));
        for (Bill bill : bills) {
            sb.append(formatBill(bill)).append("\n");
        }
        return sb.toString();
    }

    private String formatBill(Bill bill) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        double total = bill.getProductItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Facture #%d - Date: %s\n",
                bill.getId(),
                formatter.format(bill.getBillingDate())));
        sb.append(String.format("Client: %s\n", bill.getCustomer() != null ? bill.getCustomer().getName() : "N/A"));
        sb.append("Articles:\n");

        for (Bill.ProductItem item : bill.getProductItems()) {
            sb.append(String.format("  - %s x%d à %.2f€ = %.2f€\n",
                    item.getProduct() != null ? item.getProduct().getName() : "Produit #" + item.getProductId(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getQuantity() * item.getUnitPrice()));
        }

        sb.append(String.format("Total: %.2f€", total));
        return sb.toString();
    }

    public record Request(Long billId, Long customerId) {
    }

    public record Response(String message, List<Bill> bills) {
    }
}
