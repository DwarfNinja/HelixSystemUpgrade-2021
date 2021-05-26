package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.model.InventoryEntry;
import helixsystemupgrade.model.System;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

//ADMINISTRATOR FUNCTIONS, ACCESS TO ALL DATA OF ALL HELIXSYSTEMS
@Path("helixsystem")
public class HelixSystemResource {

    @GET
    @Path("{helixname}/inventory")
    @Produces("application/json")
    public String getInventoryOfHelix(@PathParam("helixname") String helixname) {
        HelixSystem helixSystem = System.getTheSystem().getHelixSystem(helixname);

        String helixInventoryJsonArray = JsonUtils.convertListToJsonArray(helixSystem.getInventoryList());
        return helixInventoryJsonArray;
    }

    @GET
    @Path("{helixname}/inventory/{id}")
    @Produces("application/json")
    public String getProductByID(@PathParam("helixname") String helixname, @PathParam("id") String id) {
        HelixSystem helixSystem = System.getTheSystem().getHelixSystem(helixname);
        InventoryEntry inventoryEntry = helixSystem.getInventoryEntrybyID(Integer.parseInt(id));

        return JsonUtils.convertObjectToJson(inventoryEntry);
    }

    @GET
    @Path("{helixname}/accounts")
    @Produces("application/json")
    public String getAllTiedAccounts(@PathParam("helixname") String helixname) {
        System theSystem = System.getTheSystem();
        HelixSystem helixSystem = theSystem.getHelixSystem(helixname);
        List<Account> tiedAccountsList = new ArrayList<>();

        for (Account account : theSystem.getAccountList()) {
            if (account.getHelixAccessList().contains(helixSystem.getName())) {
                tiedAccountsList.add(account);
            }
        }
        String tiedAccountsListJsonArray = JsonUtils.convertListToJsonArray(tiedAccountsList);
        return tiedAccountsListJsonArray;
    }
}
