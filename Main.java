import java.util.Scanner;

public class Main {
    // using static variables since we used methods...
    static double balance = 0.0;
    static String[] transactionHistory = new String[100]; //limitting the transaction to 100
    static int transactionCount = 0;
    public static void main(String[] args) {
        String userPin = "";
        Scanner userInput = new Scanner(System.in);
        // setting up the new pin
        while (!userPin.matches("\\d{4}")) {
            System.out.print("Set a new 4-digit PIN: ");
            userPin = userInput.nextLine();

            if (!userPin.matches("\\d{4}")) {
                System.out.println("Invalid PIN. Please enter exactly 4 numbers.");
            }
        }
        System.out.println("PIN successfully set!\n");
        // verifying the pin entered
        String enteredPin = "";
        while (!enteredPin.equals(userPin)) {
            System.out.print("Enter your PIN to login: ");
            enteredPin = userInput.nextLine();

            if (!enteredPin.equals(userPin)) {
                System.out.println("Wrong PIN. Try again.");
            }
        }
        System.out.println("Login successful!\n");

        // showing the main menu
        int choice = 0;
        while (choice != 5) {
            System.out.println("--- ATM MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = userInput.nextInt();
            if (choice == 1) {
                System.out.println("Current Balance: $" + balance + "\n");
            } else if (choice == 2) {
                System.out.print("Enter amount to deposit: ");
                double amount = userInput.nextDouble();
                deposit(amount);
            } else if (choice == 3) {
                System.out.print("Enter amount to withdraw: ");
                double amount = userInput.nextDouble();
                withdraw(amount);
            } else if (choice == 4) {
                showHistory();
            } else if (choice == 5) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option. Try again.\n");
            }
        }

        userInput.close();
    }

    static void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory[transactionCount] = "Deposited: $" + amount;
            transactionCount++;
            System.out.println("Successfully deposited $" + amount + "\n");
        } else {
            System.out.println("Amount must be greater than 0.\n");
        }
    }

    static void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory[transactionCount] = "Withdrew: $" + amount;
            transactionCount++;
            System.out.println("Successfully withdrew $" + amount + "\n");
        } else if (amount > balance) {
            System.out.println("Insufficient funds!\n");
        } else {
            System.out.println("Amount must be greater than 0.\n");
        }
    }
    static void showHistory() {
        System.out.println("TRANSACTION HISTORY\n\n");
        if (transactionCount == 0) {
            System.out.println("No transactions yet.");
        } else {
            for (int i = 0; i < transactionCount; i++) {
                System.out.println((i + 1) + ". " + transactionHistory[i]);
            }
        }
        System.out.println();
    }
}
