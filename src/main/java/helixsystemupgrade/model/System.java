package helixsystemupgrade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import javax.json.JsonArray;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

public class System {
    private static System theSystem = new System();

    private List<HelixSystem> helixSystemList = new ArrayList<>();

    private List<Account> accountList = new ArrayList<>();

    public System() {
        helixSystemList.add(new HelixSystem("LUMC"));
        helixSystemList.add(new HelixSystem("ErasmusMC"));
        helixSystemList.add(new HelixSystem("UMCUtrecht"));

        generateAccountList();
        assignRandomHelixAccess();
    }

    public static System getTheSystem() {
        return theSystem;
    }

    public List<HelixSystem> getHelixSystemList() {
        return helixSystemList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }


    public HelixSystem getHelixSystem(String helixname) {
        for (HelixSystem helixSystem : helixSystemList) {
            if (helixSystem.getName().equals(helixname)) {
                return helixSystem;
            }
        }
        return null;
    }

    public void addAccount(Account account) {
        if (accountList.contains(account)) {
            return;
        }
        accountList.add(account);
    }

    public Account getAccountByID(int id) {
        for (Account account : accountList) {
            if (account.getAccountID() == id) {
                return account;
            }
        }
        return null;
    }

    private void generateAccountList() {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray jsonArray = JsonUtils.getJsonArray(JsonUtils.readJsonValueFromFile("json/all-accounts.json"));
        assert jsonArray != null : "returned jsonArray of JsonUtils.getJsonArray is null!";

        for (JsonValue jsonValue : jsonArray) {
            try {
                Account account = mapper.readValue(JsonUtils.getObjectFromJsonArray(jsonArray, jsonValue), Account.class);
                accountList.add(account);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    private void assignRandomHelixAccess() {
        for (Account account : accountList) {
            int randomAmountOfHelixAccess = NumberUtils.getRandomNumberInRange(1, helixSystemList.size());
            List<HelixSystem> helixSystemListCopy = new ArrayList<>(helixSystemList);

            for (int i = 0; i < randomAmountOfHelixAccess; i++) {
                HelixSystem helixSystem = helixSystemListCopy.get(NumberUtils.getRandomNumberInRange(0, helixSystemListCopy.size()));
                account.addHelixAccess(helixSystem);
                helixSystemListCopy.remove(helixSystem);
            }
        }
    }
}
