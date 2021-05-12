package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.utils.JsonUtils;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/account")
public class AccountResource {

//    @GET
//    @Produces("application/json")
//    public String getAllAccounts() {
//        JsonArrayBuilder JsonArrBuilder = Json.createArrayBuilder();
//        List<String> myarray = new ArrayList<>();
//        HelixSystem theHelixSystem = HelixSystem.getHelixSystem();
//
//        for (Account account : theHelixSystem.getAccountList()){
//            String JsonStringOfAccount = JsonUtils.convertObjectToJson(account);
//            myarray.add(JsonStringOfAccount);
//            JsonArrBuilder.add(JsonStringOfAccount);
//        }
//        return myarray.toString();
//    }

    @GET
    @Produces("application/json")
    public String getAllAccounts() {
        HelixSystem theHelixSystem = HelixSystem.getHelixSystem();

        String jsonArray = JsonUtils.createJsonArray(theHelixSystem.getAccountList());
        return jsonArray;
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
