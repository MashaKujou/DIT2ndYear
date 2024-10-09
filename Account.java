public class Account {
    private final String accountNumber;
    private final String fname;
    private final String lname;
    private final String pin; // Changed to final as well for consistency
    private String username;
    private String password;
    private double balance;
    private boolean active;

    public Account(String accountNumber, String pin, String username, String password, String fname, String lname) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.active = true;
        this.fname = fname;
        this.lname = lname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFName() {
        return fname;
    }

    public String getLName() {
        return lname;
    }

    public void deactivate() {
        this.active = false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) { // Check for sufficient balance before withdrawing
            this.balance -= amount;
            return true;
        }
        return false; // Return false if insufficient balance
    }
}
