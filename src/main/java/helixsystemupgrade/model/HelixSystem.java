package helixsystemupgrade.model;

import java.util.ArrayList;
import java.util.List;

public class HelixSystem {
    private List<Account> accountList = new ArrayList<>();

    private static HelixSystem theHelixSystem = new HelixSystem();

    public static HelixSystem getHelixSystem() {
        return theHelixSystem;
    }
    public static void setHelixSystem(HelixSystem helixSystem) {
        theHelixSystem = helixSystem;
    }

    private HelixSystem() {
        accountList.add(new Account("John Doe", 1234));
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public Account getAccountbyID(int id) {
        for (Account account : accountList) {
            if (account.getAccountID()== id) {
                return account;
            }
        }
        return null;
    }

}
