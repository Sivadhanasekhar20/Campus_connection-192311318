// Product class
class Product {
    int id;
    String name;
    double price;
    int quantity;

    Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return price * quantity;
    }
}
//Shopping cart System
import java.util.*;
import java.io.*;

class ShoppingCart {
    ArrayList<Product> cart = new ArrayList<>();

    void addProduct(Product p) {
        cart.add(p);
        System.out.println(p.name + " added to cart.");
    }

    void removeProduct(int id) {
        cart.removeIf(p -> p.id == id);
        System.out.println("Product removed.");
    }

    void updateQuantity(int id, int qty) {
        for (Product p : cart) {
            if (p.id == id) {
                p.quantity = qty;
                System.out.println("Quantity updated.");
            }
        }
    }

    double calculateTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.getTotalPrice();
        }
        return total;
    }

    void generateBill() {
        System.out.println("\n----- BILL -----");
        for (Product p : cart) {
            System.out.println(p.name + " x " + p.quantity + " = " + p.getTotalPrice());
        }
        System.out.println("Total = " + calculateTotal());
    }

    void saveToFile() {
        try {
            FileWriter fw = new FileWriter("bill.txt", true);
            fw.write("----- BILL -----\n");
            for (Product p : cart) {
                fw.write(p.name + " x " + p.quantity + " = " + p.getTotalPrice() + "\n");
            }
            fw.write("Total = " + calculateTotal() + "\n\n");
            fw.close();
            System.out.println("Bill saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}
// Main Class
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n1.Add 2.Remove 3.Update 4.Total 5.Bill 6.Save 7.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID Name Price Quantity: ");
                    int id = sc.nextInt();
                    String name = sc.next();
                    double price = sc.nextDouble();
                    int qty = sc.nextInt();
                    cart.addProduct(new Product(id, name, price, qty));
                    break;

                case 2:
                    System.out.print("Enter ID to remove: ");
                    cart.removeProduct(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter ID and new quantity: ");
                    cart.updateQuantity(sc.nextInt(), sc.nextInt());
                    break;

                case 4:
                    System.out.println("Total Price = " + cart.calculateTotal());
                    break;

                case 5:
                    cart.generateBill();
                    break;

                case 6:
                    cart.saveToFile();
                    break;

                case 7:
                    System.exit(0);
            }
        }
    }
}
