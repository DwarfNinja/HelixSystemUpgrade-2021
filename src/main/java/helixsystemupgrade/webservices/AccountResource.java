package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.utils.JsonUtils;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/account")
public class AccountResource {

    @GET
    @Produces("application/json")
    public String getAllAccounts() {
        JsonArrayBuilder JsonArrBuilder = Json.createArrayBuilder();
        HelixSystem theHelixSystem = HelixSystem.getHelixSystem();

        for (Account account : theHelixSystem.getAccountList()){
            String JsonStringOfAccount = JsonUtils.convertObjectToJson(account);
            JsonArrBuilder.add(JsonStringOfAccount);
        }
        return JsonArrBuilder.build().toString();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getAccountByID(@PathParam("id") String id) {
        HelixSystem theHelixSystem = HelixSystem.getHelixSystem();
        try {
            if(theHelixSystem.getAccountbyID(Integer.parseInt(id)) == null) {
                throw new Exception("ERROR: Account does not exist!");
            }
            Account accountOfID = theHelixSystem.getAccountbyID(Integer.parseInt(id));
            String JsonStringOfAccount = JsonUtils.convertObjectToJson(accountOfID);
            return JsonStringOfAccount;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
