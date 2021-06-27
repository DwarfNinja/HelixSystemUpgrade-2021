package helixsystemupgrade.webservices;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;


@Path("account")
public class AccountResource {

    @GET
    @Produces("application/json")
    public Response getAccount(@Context SecurityContext securityContext) {
        if (securityContext.getUserPrincipal() instanceof Account account) {
            return Response.ok(JsonUtils.convertObjectToJson(account)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new AbstractMap.SimpleEntry<>("error", "User not found!"))
                .build();
    }

//    //TODO: To be used with product history feature
//    @GET
//    @Path("{id}/producthistory")
//    @Produces("application/json")
//    public Response getProductHistoryList(@Context SecurityContext securityContext) {
//        if (securityContext.getUserPrincipal() instanceof Account account) {
//            String productHistoryListJsonArray = JsonUtils.convertListToJsonArray(account.getProductHistoryList());
//            return Response.ok(productHistoryListJsonArray).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED)
//                .entity(new AbstractMap.SimpleEntry<>("error", "User not found!"))
//                .build();
//    }

    @GET
    @Path("notifications")
    @Produces("application/json")
    public Response getNotificationList(@Context SecurityContext securityContext) {
        if (securityContext.getUserPrincipal() instanceof Account account) {
            return Response.ok(JsonUtils.convertObjectToJson(account.getNotificationList())).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new AbstractMap.SimpleEntry<>("error", "User not found!"))
                .build();
    }

    @POST
    @Path("notifications/delete/{id}")
    @Produces("application/json")
    public Response deleteNotification(@Context SecurityContext securityContext, @PathParam("id") String id) {
        if (securityContext.getUserPrincipal() instanceof Account account) {
            if (account.getNotificationByID(Integer.parseInt(id)) != null) {

                account.getNotificationList().remove(account.getNotificationByID(Integer.parseInt(id)));
                return Response.ok().entity(new AbstractMap.SimpleEntry<>("success", "Notification removed!")).build();
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new AbstractMap.SimpleEntry<>("error", "Notification with ID: " + id + " not found!"))
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new AbstractMap.SimpleEntry<>("error", "User not found!"))
                .build();
    }
}
