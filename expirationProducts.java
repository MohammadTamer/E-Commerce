import java.time.LocalDate;
import java.util.*;

public class expirationProducts extends Product {
    private LocalDate expirationDate;

    public expirationProducts(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
