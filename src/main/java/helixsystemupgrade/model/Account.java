package helixsystemupgrade.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import javax.json.JsonArray;
import java.security.Principal;
import java.util.List;
import java.util.ArrayList;

@JsonDeserialize(as = Account.class)
public class Account implements Principal {
    private final String accountName;
    private final int accountID;
    private final String accountPassword;
    private final String accountRole;
    private final List<String> helixAccessList = new ArrayList<>();
    private final List<Product> productHistoryList = new ArrayList<>();


    //TODO: JsonIgnore
    @JsonCreator
    public Account(@JsonProperty("accountName") String accountName, @JsonProperty("accountID") int accountID,
                   @JsonProperty ("accountPassword") String accountPassword, @JsonProperty("accountRole") String accountRole) {
        this.accountName = accountName;
        this.accountID = accountID;
        this.accountPassword = accountPassword;
        this.accountRole = accountRole;
        generateRandomProductHistory();
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountID() {
        return accountID;
    }

    @JsonIgnore
    public String getPassword() {
        return accountPassword;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public List<String> getHelixAccessList() {
        return helixAccessList;
    }

    public List<Product> getProductHistoryList() {
        return productHistoryList;
    }

    public void addProduct(Product product) {
        productHistoryList.add(product);
    }

    public void addHelixAccess(HelixSystem helixSystem) {
        helixAccessList.add(helixSystem.getName());
    }

    private void generateRandomProductHistory() {
        ObjectMapper mapper = new ObjectMapper();
        int randomAmountOfProducts = NumberUtils.getRandomNumberInRange(1, 8);
        JsonArray jsonArray = JsonUtils.getJsonArray(JsonUtils.readJsonValueFromFile("json/all-products.json"));
        assert jsonArray != null : "returned jsonArray of JsonUtils.getJsonArray is null!";

        for (int i = 0; i < randomAmountOfProducts; i++) {
            try {
                Product product = mapper.readValue(JsonUtils.getRandomObjectFromJsonArray(jsonArray), Product.class);
                addProduct(product);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            return ((Account) obj).getAccountID() == getAccountID();
        }
        return false;
    }

    @Override
    public String getName() {
        return getAccountName();
    }

    public boolean checkPassword(String password) {
        return accountPassword.equals(password);
    }
}
