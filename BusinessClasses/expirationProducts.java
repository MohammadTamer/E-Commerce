package BusinessClasses;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class expirationProducts extends Product {
    private final LocalDate expirationDate;

    public expirationProducts(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public String toDataLine() {
        long daysToExpire = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        return "expirationProduct," + name + "," + price + "," + quantity + "," + daysToExpire;
    }
}
