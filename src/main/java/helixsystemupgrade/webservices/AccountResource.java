package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.model.System;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


//USER FUNCTIONS, ONLY ACCESS TO DATA TIED TO ACCOUNT
@Path("/account")
public class AccountResource {

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getAccount(@PathParam("id") String id) {
        System theSystem = System.getTheSystem();
        Account account = theSystem.getAccountByID(Integer.parseInt(id));

        return JsonUtils.convertObjectToJson(account);
    }

    @GET
    @Path("{id}/producthistory")
    @Produces("application/json")
    public String getProductHistoryList(@PathParam("id") String id) {
        System theSystem = System.getTheSystem();
        Account account = theSystem.getAccountByID(Integer.parseInt(id));

        String productHistoryListJsonArray = JsonUtils.convertListToJsonArray(account.getProductHistoryList());
        return productHistoryListJsonArray;

    }

    @GET
    @Path("{id}/helixaccess")
    @Produces("application/json")
    public String getHelixAccessList(@PathParam("id") String id) {
        System theSystem = System.getTheSystem();
        Account account = theSystem.getAccountByID(Integer.parseInt(id));

        String helixAccessListJsonArray = JsonUtils.convertListToJsonArray(account.getHelixAccessList());
        return helixAccessListJsonArray;

    }

    @GET
    @Path("{id}/{helixname}")
    @Produces("application/json")
    public String getHelixSystemInventory(@PathParam("id") String id, @PathParam("helixname") String helixname) {
        System theSystem = System.getTheSystem();
        Account account = theSystem.getAccountByID(Integer.parseInt(id));
        HelixSystem helixSystem = theSystem.getHelixSystem(helixname);

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
