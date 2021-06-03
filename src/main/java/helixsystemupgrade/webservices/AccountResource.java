package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.model.SystemApp;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.nio.file.attribute.UserPrincipalNotFoundException;


//USER FUNCTIONS, ONLY ACCESS TO DATA TIED TO ACCOUNT
@Path("/account")
public class AccountResource {

    @GET
    @Produces("application/json")
    public String getAccountHelixAccess(@Context SecurityContext securityContext) {

        try {
            if (securityContext.getUserPrincipal() instanceof Account account) {
                return JsonUtils.convertObjectToJson(account);
            }
            throw new UserPrincipalNotFoundException("No UserPrincipal found for account");
        }
        //FIXME: Return response object with error message
        catch (Exception e) {
            return e.getMessage();
        }

    }

    //TODO: Depricated for base "/account" function
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getAccount(@PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystem();
        Account account = theSystemApp.getAccountByID(Integer.parseInt(id));

        return JsonUtils.convertObjectToJson(account);
    }

    @GET
    @Path("{id}/producthistory")
    @Produces("application/json")
    public String getProductHistoryList(@PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystem();
        Account account = theSystemApp.getAccountByID(Integer.parseInt(id));

        String productHistoryListJsonArray = JsonUtils.convertListToJsonArray(account.getProductHistoryList());
        return productHistoryListJsonArray;

    }

    @GET
    @Path("{id}/helixaccess")
    @Produces("application/json")
    public String getHelixAccessList(@PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystem();
        Account account = theSystemApp.getAccountByID(Integer.parseInt(id));

        String helixAccessListJsonArray = JsonUtils.convertListToJsonArray(account.getHelixAccessList());
        return helixAccessListJsonArray;

    }

    @GET
    @Path("{id}/{helixname}")
    @Produces("application/json")
    public String getHelixSystemInventory(@PathParam("id") String id, @PathParam("helixname") String helixname) {
        SystemApp theSystemApp = SystemApp.getTheSystem();
        Account account = theSystemApp.getAccountByID(Integer.parseInt(id));
        HelixSystem helixSystem = theSystemApp.getHelixSystem(helixname);

        try {
            if (account.getHelixAccessList().contains(helixSystem.getName())) {
                String inventoryJsonArray = JsonUtils.convertListToJsonArray(helixSystem.getInventoryList());
                return inventoryJsonArray;
            }
            throw new Exception("ERROR: Account " + id + " does not have access to this HelixSystem!");

        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
