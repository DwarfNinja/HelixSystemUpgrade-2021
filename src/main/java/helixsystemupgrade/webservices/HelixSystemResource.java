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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;

@Path("helixsystem")
public class HelixSystemResource {

    SystemApp theSystemApp = SystemApp.getTheSystemApp();

    @GET
    @Path("{helixname}/inventory")
    @Produces("application/json")
    public Response getInventoryOfHelix(@Context SecurityContext securityContext, @PathParam("helixname") String helixname) {
        if (securityContext.getUserPrincipal() instanceof Account account) {

            if (theSystemApp.getHelixSystemByName(helixname) != null) {
                if (account.getHelixAccessList().contains(helixname)) {
                    HelixSystem helixSystem = SystemApp.getTheSystemApp().getHelixSystemByName(helixname);

                        String helixInventoryJsonArray = JsonUtils.convertListToJsonArray(helixSystem.getInventoryList());
                        return Response.ok(helixInventoryJsonArray).build();
                }
                return Response.status(Response.Status.FORBIDDEN)
                        .entity(new AbstractMap.SimpleEntry<>("error", "User does not have access to this page!"))
                        .build();
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new AbstractMap.SimpleEntry<>("error", "HelixSystem with name: " + helixname + " does not exist!"))
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new AbstractMap.SimpleEntry<>("error", "User not found!"))
                .build();
    }

    @GET
    @Path("{helixname}/inventory/{id}")
    @Produces("application/json")
    public Response getProductByID(@Context SecurityContext securityContext, @PathParam("helixname") String helixname, @PathParam("id") String id) {
        if (securityContext.getUserPrincipal() instanceof Account account) {

            if (theSystemApp.getHelixSystemByName(helixname) != null) {
                if (account.getHelixAccessList().contains(helixname)) {
                    HelixSystem helixSystem = SystemApp.getTheSystemApp().getHelixSystemByName(helixname);
                        InventoryEntry inventoryEntry = helixSystem.getInventoryEntrybyID(Integer.parseInt(id));

                        if (inventoryEntry != null) {
                            return Response.ok(JsonUtils.convertObjectToJson(inventoryEntry)).build();
                        }

                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity(new AbstractMap.SimpleEntry<>(
                                        "error", "InventoryEntry with ID: " + id + " does not exist in HelixSystem: " + helixname + "!"))
                                .build();
                }
                return Response.status(Response.Status.FORBIDDEN)
                        .entity(new AbstractMap.SimpleEntry<>("error", "User does not have access to this page!"))
                        .build();

            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new AbstractMap.SimpleEntry<>("error", "HelixSystem with name: " + helixname + " does not exist!"))
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new AbstractMap.SimpleEntry<>("error", "User not found!"))
                .build();
    }
}
