package helixsystemupgrade.webservices;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/testpath")
public class Resource {

    @GET
    @Produces("application/json")
    public String getAllCountries() {
        JsonObjectBuilder JsonObjBuilder = Json.createObjectBuilder();
        JsonObjBuilder.add("country", "CHEESE");
        return JsonObjBuilder.build().toString();
    }
}
