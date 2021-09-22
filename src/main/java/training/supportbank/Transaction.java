package training.supportbank;

import java.math.BigDecimal;

public class Transaction {

    private final Account sender;
    private final Account receiver;
    private final BigDecimal amount;

    public Transaction (Account initSending, Account initReceiving, BigDecimal initAmmount) {

        sender = initSending;
        receiver = initReceiving;
        amount = initAmmount;

    }

    public BigDecimal GetAmount() {
        return amount;
    }

    public Account GetSender() {
        return sender;
    }

    public Account GetReceiver() {
        return receiver;
    }

}
