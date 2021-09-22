package training.supportbank;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.*;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        LoadTransactionsFromFile("/Users/ftarthur/Desktop/Training/SupportBank-Java-Template/Transactions2014.csv");

        for (Account a : accounts) {
            System.out.print(a.GetName() + " ");
        }

        System.out.println("\nlist [all/name]");
        HandleCommandInput();

    }

    private static void HandleCommandInput() {

        System.out.print(" > ");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        String[] command = s.split(" ", 2);

        System.out.println(command[1]);


        switch(command[0].toUpperCase(Locale.ROOT)) {
            case "LIST":
                if (command[1].toUpperCase(Locale.ROOT).equals("ALL")) {
                    PrintAllTransactions();
                } else {
                    if (ValidateName(command[1]))
                        for (Transaction t : GetAccountByName(command[1]).GetInvolvedTransactions()) {
                            System.out.println(t.GetSender().GetName() + " > £" + t.GetAmount() + " > " + t.GetReceiver().GetName());
                        }
                }
        }

    }

    private static void PrintAllTransactions() {
        for (Transaction a : transactions) {
            System.out.println(a.GetSender().GetName() + " sent £" + a.GetAmount() + " to " + a.GetReceiver().GetName());
        }
        for (Account a : accounts) {
            System.out.println(a.GetName() + " has a balance of " + a.GetBalance());
        }
    }

    private static void LoadTransactionsFromFile(String fileName) {
        FileReader fr = null;

        try {
           fr = new FileReader(fileName);
           CSVReader reader = new CSVReader(fr);

           String[] line;

           while ((line = reader.readNext()) != null) {

               if (!ValidateTransaction(line))
                   continue;

               Account fromAccount = GetAccountByName(line[1]);
               Account toAccount = GetAccountByName(line[2]);
               BigDecimal amount = new BigDecimal(line[4]);

               transactions.add(new Transaction(fromAccount, toAccount, amount));

           }

        }
        catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        for (Account a : accounts) {
            PopulateUserTransactions(a);
        }

    }

    private static boolean ValidateName(String nameCheck) {
        for (Account a : accounts) {
            if (a.GetName().toUpperCase(Locale.ROOT).equals(nameCheck.toUpperCase(Locale.ROOT)))
                return true;
        }
        accounts.add(new Account(nameCheck));
        return false;
    }

    private static Account GetAccountByName(String nameCheck) {
        boolean l = ValidateName(nameCheck);
        for (Account a : accounts) {
            if (a.GetName().toUpperCase(Locale.ROOT).equals(nameCheck.toUpperCase(Locale.ROOT)))
                return a;
        }
        return null;
    }

    private static void PopulateUserTransactions(Account user) {

        for (Transaction t : transactions) {
            if (t.GetReceiver() == user | t.GetSender() == user) {
                user.AddInvolvedTransaction(t);
            }
        }

    }

    private static boolean ValidateTransaction(String[] line) {
        try {
            float v = Float.parseFloat(line[4]);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
