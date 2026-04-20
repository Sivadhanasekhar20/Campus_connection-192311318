//Loan Class
class Loan {
    int loanId;
    String customerName;
    double amount;
    double interestRate;
    int tenure; 
    String status; 
    Loan(int loanId, String customerName, double amount, double interestRate, int tenure) {
        this.loanId = loanId;
        this.customerName = customerName;
        this.amount = amount;
        this.interestRate = interestRate;
        this.tenure = tenure;
        this.status = "Pending";
    }
    double calculateEMI() {
        double r = interestRate / (12 * 100);
        return (amount * r * Math.pow(1 + r, tenure)) /
               (Math.pow(1 + r, tenure) - 1);
    }
}
import java.util.*;
import java.io.*;

class LoanSystem {
    ArrayList<Loan> loans = new ArrayList<>();

    void applyLoan(Loan loan) {
        loans.add(loan);
        System.out.println("Loan Applied Successfully.");
    }
    void viewLoans() {
        for (Loan l : loans) {
            System.out.println("ID: " + l.loanId +
                    " Name: " + l.customerName +
                    " Amount: " + l.amount +
                    " EMI: " + l.calculateEMI() +
                    " Status: " + l.status);
        }
    }
    void updateStatus(int id, String status) {
        for (Loan l : loans) {
            if (l.loanId == id) {
                l.status = status;
                System.out.println("Loan " + status);
            }
        }
    }
    void trackRepayment(int id, int paidMonths) {
        for (Loan l : loans) {
            if (l.loanId == id) {
                int remaining = l.tenure - paidMonths;
                System.out.println("Remaining Months: " + remaining);
            }
        }
    }
    void saveToFile() {
        try {
            FileWriter fw = new FileWriter("loans.txt", true);
            for (Loan l : loans) {
                fw.write(l.loanId + "," + l.customerName + "," +
                        l.amount + "," + l.status + "\n");
            }
            fw.close();
            System.out.println("Data saved.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}
//Main Class
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoanSystem system = new LoanSystem();
        while (true) {
            System.out.println("\n1.Apply Loan 2.View Loans 3.Approve 4.Reject 5.Track 6.Save 7.Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID Name Amount Interest Tenure: ");
                    int id = sc.nextInt();
                    String name = sc.next();
                    double amount = sc.nextDouble();
                    double rate = sc.nextDouble();
                    int tenure = sc.nextInt();
                    system.applyLoan(new Loan(id, name, amount, rate, tenure));
                    break;
                case 2:
                    system.viewLoans();
                    break;

                case 3:
                    System.out.print("Enter Loan ID: ");
                    system.updateStatus(sc.nextInt(), "Approved");
                    break;

                case 4:
                    System.out.print("Enter Loan ID: ");
                    system.updateStatus(sc.nextInt(), "Rejected");
                    break;

                case 5:
                    System.out.print("Enter Loan ID and Paid Months: ");
                    system.trackRepayment(sc.nextInt(), sc.nextInt());
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
