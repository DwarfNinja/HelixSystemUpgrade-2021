package helixsystemupgrade.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import helixsystemupgrade.utils.JsonUtils;
import helixsystemupgrade.utils.NumberUtils;

import java.util.List;
import java.util.ArrayList;

@JsonDeserialize(as = Account.class)
public class Account {
    private final String accountName;
    private final int accountID;
    private final List<Product> productHistoryList = new ArrayList<>();

    @JsonCreator
    public Account(@JsonProperty("accountName") String accountName, @JsonProperty("accountID") int accountID) {
        this.accountName = accountName;
        this.accountID = accountID;
        generateRandomProductHistory();
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountID() {
        return accountID;
    }

    public List<Product> getProductHistoryList() {
        return productHistoryList;
    }

    public void addProduct(Product product) {
        productHistoryList.add(product);
    }

    private void generateRandomProductHistory() {
        ObjectMapper mapper = new ObjectMapper();
        int randomAmountOfProducts = NumberUtils.getRandomNumberInRange(1, 8);
        for (int i = 0; i < randomAmountOfProducts; i++) {
            try {
                Product product = mapper.readValue(JsonUtils.getRandomObjectFromArray("json/all-products.json"), Product.class);
                addProduct(product);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
