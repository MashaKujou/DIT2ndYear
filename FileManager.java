import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "src/accounts.txt";

    public static void saveAccount(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(account.getAccountNumber() + "," +
                    account.getPin() + "," +
                    account.getBalance() + "," +
                    account.isActive() + "," +
                    account.getUsername() + "," +
                    account.getPassword() + "," +
                    account.getFName() + "," +
                    account.getLName() + "\n");
        } catch (IOException e) {
            System.err.println("Error saving account: " + e.getMessage());
        }
    }

    public static List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 8) {
                    try {
                        Account account = new Account(data[0], data[1], data[4], data[5], data[6], data[7]);
                        account.deposit(Double.parseDouble(data[2]));

                        if (!Boolean.parseBoolean(data[3])) {
                            account.deactivate();
                        }
                        accounts.add(account);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing account balance or status: " + e.getMessage());
                    }
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
        }
        return accounts;
    }

    public static void updateAccounts(List<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Account account : accounts) {
                writer.write(account.getAccountNumber() + "," +
                        account.getPin() + "," +
                        account.getBalance() + "," +
                        account.isActive() + "," +
                        account.getUsername() + "," +
                        account.getPassword() + "," +
                        account.getFName() + "," +
                        account.getLName() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error updating accounts: " + e.getMessage());
        }
    }
}
