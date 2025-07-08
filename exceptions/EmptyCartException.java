package exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() { super("BusinessClasses.Cart is empty"); }
}
