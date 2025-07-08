package BusinessClasses;

import exceptions.EmptyCartException;
import exceptions.ExpiredProductException;
import exceptions.OutOfStockException;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final ArrayList<Item> items = new ArrayList<>();

    public void add(Product p, int quantity) {
        if (quantity > 0 || quantity < p.getQuantity()) {
            Item i = new Item(p, quantity);
            items.add(i);
        } else
            throw new OutOfStockException(p.getName());
    }

    public List<Item> getItems() {
        return items;
    }

    public String checkout(Customer customer) {
        StringBuilder receiptBuilder = new StringBuilder();
        if (this.getItems().isEmpty()) {
            throw new EmptyCartException();
        }

        double totalPrice = 0;
        List<Item> items = this.getItems();
        ArrayList<Item> shippedProducts = new ArrayList<>();
        for (Item i : items) {
            Product p = i.product;
            if (p.isExpired()) {
                throw new ExpiredProductException(p.getName());
            }
            p.decrementQuantity(i.quantity);
            totalPrice += i.allItemPrice();
            if (p instanceof Shippable) {
                shippedProducts.add(i);
            }
        }

        double totalWeight = 0;
        if (!shippedProducts.isEmpty()) {
            receiptBuilder.append("** Shipment notice **\n");
            receiptBuilder.append(String.format("%-5s %-25s %12s\n", "Qty", "Item", "Weight"));
            receiptBuilder.append(String.format("%-5s %-25s %12s\n", "---", "----", "------"));

            for (Item i : shippedProducts) {
                if (i.product instanceof Shippable) {
                    double itemWeight = ((Shippable) i.product).getWeight();
                    double totalItemWeight = itemWeight * i.quantity;
                    totalWeight += totalItemWeight;

                    String formattedQty = String.format("%dx", i.quantity);
                    String formattedWeight = String.format("%.1fkg", totalItemWeight);

                    receiptBuilder.append(String.format("%-5s %-25s %12s\n", formattedQty, i.product.getName(), formattedWeight));
                }
            }
            String formattedTotalWeight = String.format("%.1fkg", totalWeight);
            receiptBuilder.append(String.format("Total package weight %23s\n\n", formattedTotalWeight));
        }

        double shippingPrice = totalWeight * 5;
        double totalAmount = totalPrice + shippingPrice;

        customer.withdrawBalance(totalAmount);

        receiptBuilder.append("** Checkout receipt **\n");
        receiptBuilder.append(String.format("%-5s %-25s %12s\n", "Qty", "Item", "Price"));
        receiptBuilder.append(String.format("%-5s %-25s %12s\n", "---", "----", "-----"));

        for (Item item : items) {
            String formattedQty = String.format("%dx", item.quantity);
            String formattedPrice = String.format("%.1f L.E.", (item.quantity * item.product.getPrice()));

            receiptBuilder.append(String.format("%-5s %-25s %12s\n", formattedQty, item.product.getName(), formattedPrice));
        }
        receiptBuilder.append("--------------------------------------------\n");

        String subtotalFormatted = String.format("%.1f L.E.", totalPrice);
        receiptBuilder.append(String.format("%-25s %17s\n", "Subtotal", subtotalFormatted));

        String shippingFormatted = String.format("%.1f L.E.", shippingPrice);
        receiptBuilder.append(String.format("%-25s %17s\n", "Shipping", shippingFormatted));

        String amountFormatted = String.format("%.1f L.E.", totalAmount);
        receiptBuilder.append(String.format("%-25s %17s\n", "Amount ", amountFormatted));

        String balanceRemainingFormatted = String.format("%.1f L.E.", customer.getBalance());
        receiptBuilder.append(String.format("Balance Remaining:    %21s\n", balanceRemainingFormatted));

        return receiptBuilder.toString();
    }}