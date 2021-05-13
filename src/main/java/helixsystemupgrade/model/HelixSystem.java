package helixsystemupgrade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helixsystemupgrade.utils.JsonUtils;
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
        addDummyAccounts();
        generateRandomProductHistory();
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

    private void addDummyAccounts() {
        accountList.add(new Account("John Doe", 1));
        accountList.add(new Account("Ponnappa Priya", 2));
        accountList.add(new Account("Hayman Andrews", 3));
        accountList.add(new Account("Verona Blair", 4));
        accountList.add(new Account("Jane Meldrum", 5));
        accountList.add(new Account("Peter Stanbridge", 6));
        accountList.add(new Account("Mia Wong", 7));
        accountList.add(new Account("Maureen M. Smith", 8));
        accountList.add(new Account("Tarryn Campbell-Gillies", 9));
        accountList.add(new Account("Daly Harry", 10));
    }

    private void generateRandomProductHistory() {
        ObjectMapper mapper = new ObjectMapper();
        for (Account account : accountList) {
            try {
                Product value = mapper.readValue(JsonUtils.getRandomObjectFromArray() , Product.class);
                account.addToProductHistoryList(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

}
