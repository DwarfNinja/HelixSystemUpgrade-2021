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
    private final List<String> helixAccessList;
    private final List<Product> productHistoryList;
    private final List<Notification> notificationList;

    // Jackson annotations used to create converts objects from JSON file to Java Objects
    @JsonCreator
    public Account(@JsonProperty("accountName") String accountName, @JsonProperty("accountID") int accountID,
                   @JsonProperty("accountPassword") String accountPassword, @JsonProperty("accountRole") String accountRole,
                   @JsonProperty("helixAccessList") List<String> helixAccessList, @JsonProperty("productHistoryList") List<Product> productHistoryList,
                   @JsonProperty("notificationList") List<Notification> notificationList) {
        this.accountName = accountName;
        this.accountID = accountID;
        this.accountPassword = accountPassword;
        this.accountRole = accountRole;
        this.helixAccessList = helixAccessList;
        this.productHistoryList = productHistoryList;
        this.notificationList = notificationList;

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

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void addProduct(Product product) {
        productHistoryList.add(product);
    }

    public void addHelixAccess(HelixSystem helixSystem) {
        helixAccessList.add(helixSystem.getHelixSystemName());
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

    public boolean checkPassword(String password) {
        return accountPassword.equals(password);
    }

    public void addRandomNotification() {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();

        List<String> messagesList = List.of("New Product!", "Product Restocked!", "Interested?");
        String randomMessage = messagesList.get(NumberUtils.getRandomNumberInRange(0, messagesList.size()));

        List<Product> copyOfProductList = new ArrayList<>(theSystemApp.getProductList());
        Product randomProduct = copyOfProductList.get(NumberUtils.getRandomNumberInRange(0, copyOfProductList.size()));

        int notificationID = notificationList.size() + 1;

        Notification randomNotification = new Notification(notificationID, randomMessage, randomProduct);
    }

    @Override
    @JsonIgnore
    public String getName() {
        return getAccountName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            return ((Account) obj).getAccountID() == getAccountID();
        }
        return false;
    }

}
