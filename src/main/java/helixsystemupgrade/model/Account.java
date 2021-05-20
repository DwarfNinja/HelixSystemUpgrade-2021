package helixsystemupgrade.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import javax.json.JsonArray;
import java.util.List;
import java.util.ArrayList;

@JsonDeserialize(as = Account.class)
public class Account {
    private final String accountName;
    private final int accountID;
    private final String password;
    private final List<String> helixAccessList = new ArrayList<>();
    private final List<Product> productHistoryList = new ArrayList<>();

    @JsonCreator
    public Account(@JsonProperty("accountName") String accountName, @JsonProperty("accountID") int accountID,
                   @JsonProperty("password") String password) {
        this.accountName = accountName;
        this.accountID = accountID;
        this.password = password;
        generateRandomProductHistory();
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getPassword() {
        return password;
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
        if (obj instanceof Account object) {
            return object.getAccountID() == getAccountID();
        }
        return false;
    }
}
