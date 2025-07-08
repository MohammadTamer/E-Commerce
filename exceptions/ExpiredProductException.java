package exceptions;

public class ExpiredProductException extends RuntimeException {
    public ExpiredProductException(String productName) {
        super("BusinessClasses.Product expired: " + productName);
    }
}
