package FilesManagement;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BusinessClasses.*;

public class ProductData {
    public static ArrayList<Product> readProducts(String filePath) {
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);

                switch (type) {
                    case "expirationProduct":
                        int daysToExpire = Integer.parseInt(parts[4]);
                        products.add(new expirationProducts(name, price, quantity, LocalDate.now().plusDays(daysToExpire)));
                        break;

                    case "shippableProduct":
                        double weight = Double.parseDouble(parts[4]);
                        LocalDate expiryDate = null;

                        if (parts.length > 5) {
                            int days = Integer.parseInt(parts[5]);
                            expiryDate = LocalDate.now().plusDays(days);
                        }

                        products.add(new shippableProducts(name, price, quantity, weight, expiryDate));
                        break;

                    case "product":
                        products.add(new Product(name, price, quantity));
                        break;

                    default:
                        System.out.println("Unknown product type: " + type);
                        break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return products;
    }
    public static void writeProducts(String filePath, List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product p : products) {
                writer.write(p.toDataLine());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
