package exceptions;

public class ExpiredProductException extends RuntimeException {
    public ExpiredProductException(String productName) {
        super("Product expired: " + productName);
    }
}
