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

//ADMINISTRATOR FUNCTIONS, ACCESS TO ALL HELIXSYSTEMS, ACCOUNTS AND DATA
@Path("systemapp")
public class SystemAppResource {

    @GET
    @Path("helixsystems")
    @Produces("application/json")
    public String getAllHelixSystems() {
        List <HelixSystem> helixSystemList = SystemApp.getTheSystem().getHelixSystemList();

        String helixSystemListJsonArray = JsonUtils.convertListToJsonArray(helixSystemList);
        return helixSystemListJsonArray;
    }

    @GET
    @Path("accounts")
    @Produces("application/json")
    public String getAllAccounts() {
        List <Account> accountList = SystemApp.getTheSystem().getAccountList();

        String accountListJsonArray = JsonUtils.convertListToJsonArray(accountList);
        return accountListJsonArray;
    }

    @GET
    @Path("accounts/{id}")
    @Produces("application/json")
    public String getAllAccountByID(@PathParam("id") String id) {
        Account account = SystemApp.getTheSystem().getAccountByID(Integer.parseInt(id));

        String accountJsonObject = JsonUtils.convertObjectToJson(account);
        return accountJsonObject;
    }

    @GET
    @Path("helixsystems/{helixname}")
    @Produces("application/json")
    public String getHelixSystemByName(@PathParam("helixname") String helixname) {
        HelixSystem helixSystem = SystemApp.getTheSystem().getHelixSystem(helixname);

        String helixSystemJsonObject= JsonUtils.convertObjectToJson(helixSystem);
        return helixSystemJsonObject;
    }

}

