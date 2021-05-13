package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.model.System;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/account")
public class AccountResource {

    @GET
    @Path("{hospital}")
    @Produces("application/json")
    public String getAllAccounts(@PathParam("hospital") String hospital) {
        System theSystem = System.getTheSystem();
        HelixSystem helixSystem = theSystem.getHelixSystem(hospital);

        String jsonArray = JsonUtils.createJsonArray(helixSystem.getAccountList());
        return jsonArray;
    }

    @GET
    @Path("{hospital}/{id}")
    @Produces("application/json")
    public String getAccountByID(@PathParam("hospital") String hospital, @PathParam("id") String id) {
        System theSystem = System.getTheSystem();
        HelixSystem helixSystem = theSystem.getHelixSystem(hospital);

        try {
            if(helixSystem.getAccountbyID(Integer.parseInt(id)) == null) {
                throw new Exception("ERROR: Account " + id + " does not exist!\"");
            }
            Account accountOfID = helixSystem.getAccountbyID(Integer.parseInt(id));
            String JsonStringOfAccount = JsonUtils.convertObjectToJson(accountOfID);
            return JsonStringOfAccount;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
