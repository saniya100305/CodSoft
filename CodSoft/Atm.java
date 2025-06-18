import java.util.Scanner;

// Represents the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0)
            this.balance = initialBalance;
        else
            this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
}

// Represents the ATM machine
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println(" Please collect your cash.");
            System.out.printf("Remaining Balance: ₹%.2f%n", account.getBalance());
        } else {
            System.out.println(" Transaction failed: Insufficient balance or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (account.deposit(amount)) {
            System.out.println(" Deposit successful.");
            System.out.printf("Updated Balance: ₹%.2f%n", account.getBalance());
        } else {
            System.out.println(" Transaction failed: Invalid deposit amount.");
        }
    }

    public void checkBalance() {
        System.out.printf(" Your current balance is: ₹%.2f%n", account.getBalance());
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the ATM!");

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("withdraw");
            System.out.println("Deposit");
            System.out.println(" Check Balance");
            System.out.println(" Exit");
            System.out.print("Enter your choice: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println(" Invalid input. Please enter a number.");
                scanner.next();  // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ₹");
                    if (scanner.hasNextDouble()) {
                        double amount = scanner.nextDouble();
                        withdraw(amount);
                    } else {
                        System.out.println(" Invalid amount entered.");
                        scanner.next();
                    }
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    if (scanner.hasNextDouble()) {
                        double amount = scanner.nextDouble();
                        deposit(amount);
                    } else {
                        System.out.println(" Invalid amount entered.");
                        scanner.next();
                    }
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println(" Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println(" Invalid choice. Please select a valid option.");
                    break;
            }
        }

        scanner.close();
    }
}

// Main class
public class Atm {
    public static void main(String[] args) {
        // Start with some initial balance (e.g. 1000)
        BankAccount account = new BankAccount(1000);
        ATM atm = new ATM(account);
        atm.start();
    }
}


