package training.supportbank;

public class Account {

    private String name;


    public Account (String initName) {
        name = initName;
    }

    public void SetName(String newName) {
        name = newName;
    }

    public String getName () {
        return name;
    }

}
