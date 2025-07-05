import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final ArrayList<Item> items = new ArrayList<>();

    public void add(Product p, int quantity) {
        if (quantity > 0 || quantity < p.getQuantity()) {
            Item i = new Item(p, quantity);
            items.add(i);
        } else
            throw new RuntimeException("Product out of stock: " + p.getName());
    }

    public List<Item> getItems() {
        return items;
    }

}
