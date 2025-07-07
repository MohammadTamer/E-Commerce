import exceptions.EmptyCartException;
import exceptions.ExpiredProductException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product cheese = new expirationProducts("Cheese", 4.5, 10, LocalDate.now().plusDays(5));
        Product tv     = new shippableProducts("TV", 300, 5, 15.0);
        Product card   = new Product("Scratch Card", 1, 100) {};

        Customer cust = new Customer("Ali", 1000);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(tv, 1);
        cart.add(card, 5);

        checkout(cust, cart);
    }



    public static void checkout(Customer customer, Cart cart) {
        if (cart.getItems().isEmpty())
            throw new EmptyCartException();
        double totalPrice = 0;
        double shippingPrice = 0;
        List<Item> items = cart.getItems();
        ArrayList<Item> shippedProducts = new ArrayList<>();
        for (Item i : items) {
            Product p = i.product;
            if (p.isExpired())
                throw new ExpiredProductException(p.getName());
            p.decrementQuantity(i.quantity);
            totalPrice += i.allItemPrice();

            if (p instanceof Shippable)
                shippedProducts.add(i);
        }


        double totalWeight = 0;
        if (!shippedProducts.isEmpty()) {
            System.out.println("** Shipment notice **");
            for (Item i : shippedProducts) {
                double itemWeight = ((Shippable) i.product).getWeight();
                double totalItemWeight = itemWeight * i.quantity;
                totalWeight += totalItemWeight;
                System.out.printf(i.quantity + "x " + i.product.getName() + "  " + (int) (totalItemWeight * 1000) + "g%n");
            }
            System.out.printf("Total package weight " + totalWeight + "kg%n%n");
        }


        shippingPrice = totalWeight * 5;
        double totalAmount = totalPrice + shippingPrice;
        customer.withdrawBalance(totalAmount);
        System.out.println("** Checkout receipt **");
        for (Item item : items) {
            System.out.println(item.quantity + "x " + item.product.getName() + "  " + (int) (item.quantity * item.product.getPrice()));
        }
        System.out.println("-----------------------");
        System.out.println("Subtotal  " + (int)totalPrice);
        System.out.println("Shipping  " + (int)shippingPrice);
        System.out.println("Amount    " + (int)totalAmount);
    }

}
