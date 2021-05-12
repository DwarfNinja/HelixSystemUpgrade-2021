package helixsystemupgrade.webservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.utils.JsonUtils;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.Map;

@Path("/account")
public class AccountResource {

    @GET
    @Produces("application/json")
    public String getAllAccounts() {
        JsonObjectBuilder JsonObjBuilder = Json.createObjectBuilder();
        JsonArrayBuilder JsonArrBuilder = Json.createArrayBuilder();
        HelixSystem theHelixSystem = HelixSystem.getHelixSystem();

        for (Account account : theHelixSystem.getAccountList()){
            JsonObjBuilder.add("account_name", account.getName());
            JsonObjBuilder.add("account_id", account.getAccountID());
            JsonArrBuilder.add(JsonObjBuilder);
        }
        return JsonArrBuilder.build().toString();
    }

//    @GET
//    @Path("{id}")
//    @Produces("application/json")
//    public String getAccountByID(@PathParam("id") String id) {
//        HelixSystem theHelixSystem = HelixSystem.getHelixSystem();
//        Account accountOfID = theHelixSystem.getAccountbyID(Integer.parseInt(id));
//        ObjectMapper objMapper = new ObjectMapper();
//
//        try {
//            // Converting the Java object into a JSON string
//            String jsonStringOfAccount = objMapper.writeValueAsString(accountOfID);
//            // Displaying Java object into a JSON string
//            return jsonStringOfAccount;
//        }
//        catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "ERROR: JsonProcessingException!";
//        }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getAccountByID(@PathParam("id") String id) {
        HelixSystem theHelixSystem = HelixSystem.getHelixSystem();
        Account accountOfID = theHelixSystem.getAccountbyID(Integer.parseInt(id));

        String JsonStringOfAccount = JsonUtils.convertObjectToJson(accountOfID);
        return JsonStringOfAccount;
    }
}
