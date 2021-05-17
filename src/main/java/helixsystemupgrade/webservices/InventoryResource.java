package helixsystemupgrade.webservices;

import helixsystemupgrade.model.HelixSystem;
import helixsystemupgrade.model.InventoryEntry;
import helixsystemupgrade.model.System;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/inventory")
public class InventoryResource {

    @GET
    @Path("{hospital}")
    @Produces("application/json")
    public String getInventory(@PathParam("hospital") String hospital) {
        HelixSystem helixSystem = System.getTheSystem().getHelixSystem(hospital);

        String jsonArray = JsonUtils.convertToJsonArray(helixSystem.getInventoryList());
        return jsonArray;
    }

    @GET
    @Path("{hospital}/{id}")
    @Produces("application/json")
    public String getProductByID(@PathParam("hospital") String hospital, @PathParam("id") String id) {
        HelixSystem helixSystem = System.getTheSystem().getHelixSystem(hospital);
        try {
            if(helixSystem.getinventoryEntrybyID(Integer.parseInt(id)) == null) {
                throw new Exception("ERROR: Product " + id + " does not exist!");
            }
            InventoryEntry inventoryEntryOfID = helixSystem.getinventoryEntrybyID(Integer.parseInt(id));
            String JsonStringOfProduct = JsonUtils.convertObjectToJson(inventoryEntryOfID);
            return JsonStringOfProduct;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
