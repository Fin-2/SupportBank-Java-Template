package training.supportbank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String name;
    private List<Transaction> involvedTransactions;
    private BigDecimal balance;


    public Account (String initName) {

        name = initName;
        involvedTransactions = new ArrayList<>();
        balance = new BigDecimal(0);

    }

    private void ChangeBalance(BigDecimal modifier, boolean sender) {
        balance = sender ? balance.subtract(modifier) : balance.add(modifier);
    }

    public BigDecimal GetBalance() {
        return balance;
    }

    public void SetName(String newName) {
        name = newName;
    }

    public String GetName() {
        return name;
    }

    public void AddInvolvedTransaction(Transaction t) {
        involvedTransactions.add(t);
        ChangeBalance(t.GetAmount(), (t.GetSender() == this));
    }

    public List<Transaction> GetInvolvedTransactions() {
        return involvedTransactions;
    }

}
