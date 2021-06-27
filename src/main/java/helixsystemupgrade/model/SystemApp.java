package helixsystemupgrade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import javax.json.JsonArray;
import javax.json.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class SystemApp {

    private static SystemApp theSystemApp;

    private final List<HelixSystem> helixSystemList = new ArrayList<>();
    private final List<Account> accountList = new ArrayList<>();
    private final List<Product> productList = new ArrayList<>();

    private SystemApp() {
        helixSystemList.add(new HelixSystem("LUMC"));
        helixSystemList.add(new HelixSystem("ErasmusMC"));
        helixSystemList.add(new HelixSystem("UMCUtrecht"));

        generateAccountList();
        generateProductList();

        for (Account account : accountList) {
            if (account.getHelixAccessList().isEmpty()) {
                assignRandomHelixAccess(account);
            }
            account.generateRandomProductHistory(productList);
            account.generateRandomNotifications(helixSystemList);
        }
    }

    public static SystemApp getTheSystemApp() {
        if (theSystemApp == null) {
            theSystemApp = new SystemApp();
        }
        return theSystemApp;
    }

    public List<HelixSystem> getHelixSystemList() {
        return helixSystemList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public HelixSystem getHelixSystemByName(String helixname) {
        for (HelixSystem helixSystem : helixSystemList) {
            if (helixSystem.getHelixSystemName().equals(helixname)) {
                return helixSystem;
            }
        }
        return null;
    }

    public Account getAccountByID(int id) {
        for (Account account : accountList) {
            if (account.getAccountID() == id) {
                return account;
            }
        }
        return null;
    }

    public Account getAccountByName(String name) {
        for (Account account : accountList) {
            if (account.getAccountName().equals(name)) {
                return account;
            }
        }
        return null;
    }

    public void addHelixSystem(HelixSystem helixSystem) {
        if (!helixSystemList.contains(helixSystem)) {
            helixSystemList.add(helixSystem);
        }

    }

    public void addAccount(Account account) {
        if (accountList.contains(account)) {
            return;
        }
        accountList.add(account);
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

    private void generateProductList() {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray jsonArray = JsonUtils.getJsonArray(JsonUtils.readJsonValueFromFile("json/all-products.json"));
        assert jsonArray != null : "returned jsonArray of JsonUtils.getJsonArray is null!";

        for (JsonValue jsonValue : jsonArray) {
            try {
                Product product = mapper.readValue(JsonUtils.getObjectFromJsonArray(jsonArray, jsonValue), Product.class);
                productList.add(product);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    private void assignRandomHelixAccess(Account account) {
        int randomAmountOfHelixAccess = NumberUtils.getRandomNumberInRange(1, helixSystemList.size());
        List<HelixSystem> helixSystemListCopy = new ArrayList<>(helixSystemList);

        for (int i = 0; i < randomAmountOfHelixAccess; i++) {
            HelixSystem randomHelixSystem = helixSystemListCopy.get(NumberUtils.getRandomNumberInRange(0, helixSystemListCopy.size()));
            account.addHelixAccess(randomHelixSystem);
            helixSystemListCopy.remove(randomHelixSystem);
        }
    }

    public String validateCredentials(String name, String password){
        if(name==null || name.isBlank() || password == null || password.isBlank()) {
            return null;
        }

        Account myAccount = getAccountByName(name);
        if (myAccount != null) {
            if (myAccount.checkPassword(password)) {
                return myAccount.getAccountRole();
            }
        }
        return null;
    }
}
