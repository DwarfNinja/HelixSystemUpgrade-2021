package helixsystemupgrade.webservices;

import helixsystemupgrade.model.*;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;
import java.util.List;

@Path("test")
public class TestResource {

    @GET
    @Path("account")
    @Produces("application/json")
    public Response getAllAccounts() {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        List<Account> accountList = theSystemApp.getAccountList();

        String productHistoryListJsonArray = JsonUtils.convertListToJsonArray(accountList);
        return Response.ok(productHistoryListJsonArray).build();
    }

    @GET
    @Path("account/{id}")
    @Produces("application/json")
    public Response getAccountByID(@PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        try {
            Account account = theSystemApp.getAccountByID(Integer.parseInt(id));
            String accountJsonObject = JsonUtils.convertObjectToJson(account);
            return Response.ok(accountJsonObject).build();
        }
        catch(NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new AbstractMap.SimpleEntry<>("error", "Given account ID is not an integer!"))
                    .build();
        }
    }

    @GET
    @Path("helixsystem")
    @Produces("application/json")
    public Response getAllHelixSystems() {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        List<HelixSystem> helixSystemList = theSystemApp.getHelixSystemList();

        String helixSystemListJsonArray = JsonUtils.convertListToJsonArray(helixSystemList);
        return Response.ok().entity(helixSystemListJsonArray).build();
    }

    @GET
    @Path("helixsystem/{name}")
    @Produces("application/json")
    public Response getHelixSystemByName(@PathParam("name") String name) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        HelixSystem helixSystem = theSystemApp.getHelixSystemByName(name);
        if (helixSystem != null) {
            List<InventoryEntry> helixInventoryList = helixSystem.getInventoryList();
            String helixInventoryListJsonArray = JsonUtils.convertListToJsonArray(helixInventoryList);
            return Response.ok(helixInventoryListJsonArray).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new AbstractMap.SimpleEntry<>("error", "HelixSystem with name: " + name + " does not exist!"))
                .build();
    }

    @GET
    @Path("helixsystem/{name}/inventory")
    @Produces("application/json")
    public Response getHelixSystemInventory(@PathParam("name") String name) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        HelixSystem helixSystem = theSystemApp.getHelixSystemByName(name);
        if (helixSystem != null) {
            String helixSystemJsonObject = JsonUtils.convertObjectToJson(helixSystem);
            return Response.ok(helixSystemJsonObject).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new AbstractMap.SimpleEntry<>("error", "HelixSystem with name: " + name + " does not exist!"))
                .build();
    }

    @GET
    @Path("helixsystem/{name}/inventory/{id}")
    @Produces("application/json")
    public Response getProductByID(@PathParam("name") String name, @PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        HelixSystem helixSystem = theSystemApp.getHelixSystemByName(name);
        if (helixSystem != null) {
            try {
                InventoryEntry inventoryEntry = helixSystem.getInventoryEntrybyID(Integer.parseInt(id));
                if (inventoryEntry != null) {
                    String inventoryEntryJsonObject = JsonUtils.convertObjectToJson(inventoryEntry);
                    return Response.ok(inventoryEntryJsonObject).build();
                }
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new AbstractMap.SimpleEntry<>("error", "Product with ID " + id + " does not exist in this HelixSystem!"))
                        .build();

            }
            catch(NumberFormatException nfe) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new AbstractMap.SimpleEntry<>("error", "Given Product ID is not an integer!"))
                        .build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new AbstractMap.SimpleEntry<>("error", "HelixSystem with name: " + name + " does not exist!"))
                .build();
    }

    @GET
    @Path("account/{id}/notifications")
    @Produces("application/json")
    public Response getNotificationList(@PathParam("id") String id) {
        SystemApp theSystemApp = SystemApp.getTheSystemApp();
        try {
            Account account = theSystemApp.getAccountByID(Integer.parseInt(id));
            if (account != null) {
                String accountNotificationJsonArray = JsonUtils.convertListToJsonArray(account.getNotificationList());
                return Response.ok(accountNotificationJsonArray).build();
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new AbstractMap.SimpleEntry<>("error", "Account with ID " + id + " does not exist!"))
                    .build();

        } catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new AbstractMap.SimpleEntry<>("error", "Given account ID is not an integer!"))
                    .build();
        }
    }
}
