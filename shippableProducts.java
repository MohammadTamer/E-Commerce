import java.time.LocalDate;

public class shippableProducts extends Product implements Shippable {
    double weight;
    LocalDate expirationDate;

    public shippableProducts(String name, double price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    public shippableProducts(String name, double price, int quantity, double weight) {
        this(name, price, quantity, weight, null);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    public boolean isExpired() {
        if(expirationDate != null)
            return LocalDate.now().isAfter(expirationDate);
        else
            return false;
    }
}
