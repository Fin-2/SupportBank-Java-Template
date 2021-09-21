package training.supportbank;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {

        LoadTransactionsFromFile("/Users/ftarthur/Desktop/Training/SupportBank-Java-Template/Transactions2014.csv");

        for (Transaction a : transactions) {
            System.out.println(a.GetSender().getName() + " sent £" + a.GetAmount() + " to " + a.GetReceiver().getName());
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
               Float amount = Float.parseFloat(line[4]);

               transactions.add(new Transaction(fromAccount, toAccount, amount));

           }

        }
        catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private static boolean ValidateName(String nameCheck) {
        for (Account a : accounts) {
            if (a.getName().equals(nameCheck))
                return true;
        }
        accounts.add(new Account(nameCheck));
        return false;
    }

    private static Account GetAccountByName(String nameCheck) {
        boolean l = ValidateName(nameCheck);
        for (Account a : accounts) {
            if (a.getName().equals(nameCheck))
                return a;
        }
        return null;
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
