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
    double getTotal() {
        return price * quantity;
    }
}
import java.util.*;
import java.io.*;
class BillingSystem {
    ArrayList<Product> items = new ArrayList<>();
    double discount = 0;
    void addItem(Product p) {
        items.add(p);
        System.out.println(p.name + " added.");
    }
    double calculateTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.getTotal();
        }
        return total;
    }
    void applyDiscount(double percent) {
        discount = percent;
        System.out.println("Discount Applied: " + percent + "%");
    }
    void generateBill() {
        double total = calculateTotal();
        double discountAmount = total * discount / 100;
        double finalAmount = total - discountAmount;

        System.out.println("\n------ BILL ------");
        for (Product p : items) {
            System.out.println(p.name + " x " + p.quantity + " = " + p.getTotal());
        }
        System.out.println("Total: " + total);
        System.out.println("Discount: " + discountAmount);
        System.out.println("Final Amount: " + finalAmount);
    }
    void saveInvoice() {
        try {
            FileWriter fw = new FileWriter("invoice.txt", true);
            double total = calculateTotal();
            double discountAmount = total * discount / 100;
            double finalAmount = total - discountAmount;

            fw.write("------ BILL ------\n");
            for (Product p : items) {
                fw.write(p.name + " x " + p.quantity + " = " + p.getTotal() + "\n");
            }
            fw.write("Total: " + total + "\n");
            fw.write("Discount: " + discountAmount + "\n");
            fw.write("Final Amount: " + finalAmount + "\n\n");

            fw.close();
            System.out.println("Invoice saved.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BillingSystem bill = new BillingSystem();
        while (true) {
            System.out.println("\n1.Add Item 2.Total 3.Discount 4.Generate Bill 5.Save 6.Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID Name Price Quantity: ");
                    int id = sc.nextInt();
                    String name = sc.next();
                    double price = sc.nextDouble();
                    int qty = sc.nextInt();
                    bill.addItem(new Product(id, name, price, qty));
                    break;
                case 2:
                    System.out.println("Total = " + bill.calculateTotal());
                    break;
                case 3:
                    System.out.print("Enter discount %: ");
                    bill.applyDiscount(sc.nextDouble());
                    break;
                case 4:
                    bill.generateBill();
                    break;
                case 5:
                    bill.saveInvoice();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
}
