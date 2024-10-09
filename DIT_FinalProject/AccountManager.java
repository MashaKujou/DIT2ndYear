import java.util.List;

public class AccountManager {
    private final List<Account> accounts;

    public AccountManager() {
        accounts = FileManager.loadAccounts(); // Load existing accounts from the file
    }

    public Account createAccount(String username, String password, String fname, String lname) {
        String accountNumber = generateAccountNumber();
        String pin = generateUniquePin();

        Account newAccount = new Account(accountNumber, pin, username, password, fname, lname);
        accounts.add(newAccount);
        FileManager.saveAccount(newAccount); // Save the new account to the file
        return newAccount;
    }

    public Account login(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password) && account.isActive()) {
                return account; // Return the account if username and password match the txt file
            }
        }
        return null; // return if No matching account found
    }
    public void saveAccounts() {
        FileManager.updateAccounts(accounts); // Update all accounts in the file
    }

    private String generateAccountNumber() {
        return String.valueOf(System.currentTimeMillis()); //using timestamp as account number
    }

    private String generateUniquePin() {
        return String.valueOf((int)(Math.random() * 10000)); //random 4-digit PIN
    }
}
