import exceptions.InsufficientBalanceException;
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

}
