package helixsystemupgrade.model;

public class Account {
    private String name;
    private int accountID;

    public Account(String name, int accountID) {
        this.name = name;
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public int getAccountID() {
        return accountID;
    }

//    @Override
//    public String toString() {
//        return "\"Account [Name "+ name + ", Account_ID = " + accountID + "]";
//    }
}
