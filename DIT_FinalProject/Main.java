import java.util.Scanner;

public class Main {
    static AccountManager manager = new AccountManager();
    static Scanner scanner = new Scanner(System.in);
    static Account account;
    static boolean sessionActive = true;

    public static void main(String[] args) {
        System.out.println("\nWelcome to the Banking System");

        while (sessionActive) {
            System.out.println("\n-------------------------------------------------------");
            System.out.print("1. Create Account\n2. Login\n3. Exit");
            System.out.println("\n------------------------------------------------------- \nSelect Option:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("\nEnter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    Account newAccount = manager.createAccount(username, password, firstName, lastName);
                    System.out.println("\nAccount created! \nAccount Number: " 
                        + newAccount.getAccountNumber() + "\nPIN: " 
                        + newAccount.getPin() + "\nFirst Name: " 
                        + newAccount.getFName() + "\nLast Name: " 
                        + newAccount.getLName());
                }
                case 2 -> {
                    sessionActive = true;
                    System.out.print("\nEnter Username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPassword = scanner.nextLine();
                    account = manager.login(loginUsername, loginPassword);

                    if (account != null) {
                        System.out.println("Enter your PIN: ");
                        String pin = scanner.next();

                        if (account.getPin().equals(pin)) {
                            actionChoice();
                        } else {
                            System.out.println("Incorrect PIN.");
                        }
                    } else {
                        System.out.println("Invalid credentials or account inactive.");
                    }
                }
                case 3 -> {
                    System.out.println("Thank you for bamking with us. Goodbye!");
                    return; //exit the loop
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void actionChoice() {
        boolean actionSession = true;
        System.out.println("Login successful!");

        while (actionSession) {
            System.out.println("\nChoose an option:");
            System.out.print("1. Deposit\n2. Withdraw\n3. Check Info\n4. Logout\n5. Disable Account\nSelect option: ");
            int actionChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (actionChoice) {
                case 1 -> {
                    // Deposit
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount <= 10000) {
                        account.deposit(depositAmount);
                        System.out.println("Deposited: $" + depositAmount + ". New balance: $" + account.getBalance());
                    } else if (depositAmount > 10000) {
                        System.out.println("Error: Cannot deposit more than $10,000.");
                    } else {
                        System.out.println("Please input a valid number.");
                    }
                }
                case 2 -> {
                    // Withdraw
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount > 350000) {
                        System.out.println("Error: Cannot withdraw more than $350,000.");
                    } else if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrew: $" + withdrawAmount + ". New balance: $" + account.getBalance());
                    } else {
                        System.out.println("Insufficient balance for withdrawal.");
                    }
                }
                case 3 -> {
                    // Check Info
                    System.out.println("\nAccount Info");
                    System.out.println("First Name: " + account.getFName());
                    System.out.println("Last Name: " + account.getLName());
                    System.out.println("Card Number: " + account.getAccountNumber());
                    System.out.println("Your current balance is: $" + account.getBalance());
                }
                case 4 -> {
                    // Logout
                    System.out.println("Logging out and saving account balance...");
                    manager.saveAccounts();
                    System.out.println("Logged out successfully.");
                    actionSession = false;
                }
                case 5 -> {
                    // Deactivate Account
                    account.deactivate();
                    manager.saveAccounts();
                    System.out.println("This account '" + account.getUsername() + "' has been deactivated.");
                    actionSession = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
