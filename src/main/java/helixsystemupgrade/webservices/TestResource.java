package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.model.SystemApp;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("test")
public class TestResource {

    @GET
    @Path("account")
    @Produces("application/json")
    public String getAllAccounts() {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        List<Account> accountList = theSystemApp.getAccountList();

        String productHistoryListJsonArray = JsonUtils.convertListToJsonArray(accountList);
        return productHistoryListJsonArray;
    }

    @GET
    @Path("account/{id}")
    @Produces("application/json")
    public String getAccountByID(@PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        Account account = theSystemApp.getAccountByID(Integer.parseInt(id));

        String productHistoryListJsonArray = JsonUtils.convertObjectToJson(account);
        return productHistoryListJsonArray;
    }

    @GET
    @Path("helixsystem")
    @Produces("application/json")
    public String getAllHelixSystems() {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        List<HelixSystem> helixSystemList = theSystemApp.getHelixSystemList();

        String productHistoryListJsonArray = JsonUtils.convertListToJsonArray(helixSystemList);
        return productHistoryListJsonArray;
    }

    @GET
    @Path("helixsystem/{name}")
    @Produces("application/json")
    public String getHelixSystemByName(@PathParam("name") String name) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        HelixSystem helixSystem = theSystemApp.getHelixSystemByName(name);

        String productHistoryListJsonArray = JsonUtils.convertObjectToJson(helixSystem);
        return productHistoryListJsonArray;
    }
}
