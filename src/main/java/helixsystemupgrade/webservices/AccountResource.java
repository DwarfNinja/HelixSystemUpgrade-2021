package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.SystemApp;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.nio.file.attribute.UserPrincipalNotFoundException;


@Path("account")
public class AccountResource {

    @GET
    @Produces("application/json")
    public String getAccount(@Context SecurityContext securityContext) {
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

    @GET
    @Path("{id}/producthistory")
    @Produces("application/json")
    public String getProductHistoryList(@PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        Account account = theSystemApp.getAccountByID(Integer.parseInt(id));

        String productHistoryListJsonArray = JsonUtils.convertListToJsonArray(account.getProductHistoryList());
        return productHistoryListJsonArray;

    }

    @GET
    @Path("{id}/helixaccess")
    @Produces("application/json")
    public String getHelixAccessList(@PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        Account account = theSystemApp.getAccountByID(Integer.parseInt(id));

        String helixAccessListJsonArray = JsonUtils.convertListToJsonArray(account.getHelixAccessList());
        return helixAccessListJsonArray;
    }

    @GET
    @Path("notifications")
    @Produces("application/json")
    public String getNotificationList(@Context SecurityContext securityContext) {

        try {
            if (securityContext.getUserPrincipal() instanceof Account account) {
                return JsonUtils.convertObjectToJson(account.getNotificationList());
            }
            throw new UserPrincipalNotFoundException("No UserPrincipal found for account");
        }
        //FIXME: Return response object with error message
        catch (Exception e) {
            return e.getMessage();
        }
    }

}
