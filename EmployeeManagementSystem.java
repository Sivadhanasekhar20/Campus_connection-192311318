class Employee {
    int id;
    String name;
    double basicSalary;
    double bonus;
    Employee(int id, String name, double basicSalary, double bonus) {
        this.id = id;
        this.name = name;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
    }
    double calculateSalary() {
        return basicSalary + bonus;
    }
}
import java.util.*;
import java.io.*;

class EmployeeSystem {
    ArrayList<Employee> employees = new ArrayList<>();
    void addEmployee(Employee e) {
        employees.add(e);
        System.out.println("Employee added.");
    }
    void updateEmployee(int id, String name, double salary, double bonus) {
        for (Employee e : employees) {
            if (e.id == id) {
                e.name = name;
                e.basicSalary = salary;
                e.bonus = bonus;
                System.out.println("Employee updated.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }
    void deleteEmployee(int id) {
        employees.removeIf(e -> e.id == id);
        System.out.println("Employee deleted.");
    }
    void searchEmployee(int id) {
        for (Employee e : employees) {
            if (e.id == id) {
                System.out.println("ID: " + e.id + " Name: " + e.name +
                        " Salary: " + e.calculateSalary());
                return;
            }
        }
        System.out.println("Employee not found.");
    }
    void displayAll() {
        for (Employee e : employees) {
            System.out.println("ID: " + e.id + " Name: " + e.name +
                    " Salary: " + e.calculateSalary());
        }
    }
    void saveToFile() {
        try {
            FileWriter fw = new FileWriter("employees.txt", true);
            for (Employee e : employees) {
                fw.write(e.id + "," + e.name + "," +
                        e.basicSalary + "," + e.bonus + "\n");
            }
            fw.close();
            System.out.println("Data saved.");
        } catch (IOException ex) {
            System.out.println("Error saving file.");
        }
    }
}
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeSystem system = new EmployeeSystem();
        while (true) {
            System.out.println("\n1.Add 2.Update 3.Delete 4.Search 5.Display 6.Save 7.Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID Name Salary Bonus: ");
                    system.addEmployee(new Employee(
                            sc.nextInt(),
                            sc.next(),
                            sc.nextDouble(),
                            sc.nextDouble()));
                    break;
                case 2:
                    System.out.print("Enter ID Name Salary Bonus: ");
                    system.updateEmployee(
                            sc.nextInt(),
                            sc.next(),
                            sc.nextDouble(),
                            sc.nextDouble());
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    system.deleteEmployee(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    system.searchEmployee(sc.nextInt());
                    break;

                case 5:
                    system.displayAll();
                    break;

                case 6:
                    system.saveToFile();
                    break;

                case 7:
                    System.exit(0);
            }
        }
    }
}
