package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.model.InventoryEntry;
import helixsystemupgrade.model.SystemApp;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Path("helixsystem")
public class HelixSystemResource {

    @GET
    @Path("{helixname}/inventory")
    @Produces("application/json")
    public String getInventoryOfHelix(@Context SecurityContext securityContext, @PathParam("helixname") String helixname) {
        try {
            if (securityContext.getUserPrincipal() instanceof Account account) {
                if (account.getHelixAccessList().contains(helixname)) {

                    HelixSystem helixSystem = SystemApp.getTheSystemApp().getHelixSystemByName(helixname);
                    String helixInventoryJsonArray = JsonUtils.convertListToJsonArray(helixSystem.getInventoryList());
                    return helixInventoryJsonArray;
                }
                throw new Exception("User does not have access to this HelixSystem");
            }
            throw new UserPrincipalNotFoundException("No UserPrincipal found for account");
        }
        //FIXME: Return response object with error message
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @GET
    @Path("{helixname}/inventory/{id}")
    @Produces("application/json")
    public String getProductByID(@PathParam("helixname") String helixname, @PathParam("id") String id) {
        HelixSystem helixSystem = SystemApp.getTheSystemApp().getHelixSystemByName(helixname);

        InventoryEntry inventoryEntry = helixSystem.getInventoryEntrybyID(Integer.parseInt(id));
        return JsonUtils.convertObjectToJson(inventoryEntry);
    }

    @GET
    @Path("{helixname}/accounts")
    @Produces("application/json")
    public String getAllTiedAccounts(@PathParam("helixname") String helixname) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        HelixSystem helixSystem = theSystemApp.getHelixSystemByName(helixname);
        List<Account> tiedAccountsList = new ArrayList<>();

        for (Account account : theSystemApp.getAccountList()) {
            if (account.getHelixAccessList().contains(helixSystem.getHelixSystemName())) {
                tiedAccountsList.add(account);
            }
        }
        String tiedAccountsListJsonArray = JsonUtils.convertListToJsonArray(tiedAccountsList);
        return tiedAccountsListJsonArray;
    }
}
