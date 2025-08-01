package BusinessClasses;

import exceptions.OutOfStockException;

public class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity(int amount) {
        if (amount > quantity)
            throw new OutOfStockException(name);
        else
            quantity -= amount;
    }

    public boolean isExpired() {
        return false;
    }

    public String toDataLine() {
        return "product," + name + "," + price + "," + quantity;
    }
}

