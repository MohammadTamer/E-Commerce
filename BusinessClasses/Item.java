package BusinessClasses;

public class Item {
    public Product product;
    public int quantity;

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double allItemPrice() {
        return product.getPrice() * quantity;
    }
}