package training.supportbank;

public class Transaction {

    private final Account sender;
    private final Account receiver;
    private final Float amount;

    public Transaction (Account initSending, Account initReceiving, Float initAmmount) {

        sender = initSending;
        receiver = initReceiving;
        amount = initAmmount;

    }

    public float GetAmount() {
        return amount;
    }

    public Account GetSender() {
        return sender;
    }

    public Account GetReceiver() {
        return receiver;
    }

}
