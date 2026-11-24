import java.util.*;
class Account {
    private String accountNumber;
    private String name;
    private double balance;
    Account(String accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Money deposited successfully!");
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Money withdrawn successfully!");
        } else {
            System.out.println("Invalid amount or insufficient balance!");
        }
    }

    public void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: Rs. " + balance);
        System.out.println("--------------------------");
    }
}

public class BankAccountManager {

    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Search account
    public static Account searchAccount(String number) {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(number)) {
                return a;
            }
        }
        return null;
    }

    // Create account
    public static void createAccount() {
        System.out.print("Enter Account Number: ");
        String number = sc.nextLine();

        if (searchAccount(number) != null) {
            System.out.println("Account already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Opening Balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        if (balance < 0) {
            System.out.println("Invalid balance!");
            return;
        }

        accounts.add(new Account(number, name, balance));
        System.out.println("Account created successfully!");
    }

    // Deposit money
    public static void depositMoney() {
        System.out.print("Enter Account Number: ");
        String number = sc.nextLine();

        Account a = searchAccount(number);

        if (a != null) {
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            a.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw money
    public static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        String number = sc.nextLine();

        Account a = searchAccount(number);

        if (a != null) {
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            a.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Check balance
    public static void checkBalance() {
        System.out.print("Enter Account Number: ");
        String number = sc.nextLine();

        Account a = searchAccount(number);

        if (a != null) {
            System.out.println("Current Balance: Rs. " + a.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    // Display all accounts
    public static void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available!");
            return;
        }

        for (Account a : accounts) {
            a.display();
        }
    }

    // Sort accounts by name
    public static void sortAccounts() {
        Collections.sort(accounts,
            Comparator.comparing(Account::getName,
            String.CASE_INSENSITIVE_ORDER));

        System.out.println("Accounts sorted by name!");
        displayAccounts();
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== BANK ACCOUNT MANAGER =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Search Account");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Sort Accounts");
            System.out.println("8. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    String number = sc.nextLine();

                    Account a = searchAccount(number);

                    if (a != null) {
                        a.display();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 6:
                    displayAccounts();
                    break;

                case 7:
                    sortAccounts();
                    break;

                case 8:
                    System.out.println("Thank you for using Bank Manager!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 8);

        sc.close();
    }
}