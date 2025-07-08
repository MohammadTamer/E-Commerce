package exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String productName) {
        super("BusinessClasses.Product out of stock: " + productName);
    }
}
