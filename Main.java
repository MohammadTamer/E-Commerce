import BusinessClasses.*;
import FilesManagement.ProductData;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> products = ProductData.readProducts("FilesManagement/test_products.txt");

        Customer cust = new Customer("Ali", 2000);
        Cart cart = new Cart();
        cart.add(products.get(0), 3);
        cart.add(products.get(5), 1);
        cart.add(products.get(12), 3);
        String result = cart.checkout(cust);
        System.out.println(result);

        ProductData.writeProducts("FilesManagement/test_products.txt",products);
    }



}
