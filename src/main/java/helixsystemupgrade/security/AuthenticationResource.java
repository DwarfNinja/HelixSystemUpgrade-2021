package helixsystemupgrade.security;

import helixsystemupgrade.model.SystemApp;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.security.Key;

import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;


@Path("/authentication")
public class AuthenticationResource {

    SystemApp theSystemApp = SystemApp.getTheSystem();
    final static public Key key = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {

        try {
            // Authenticate the user using the model, because ... no database
            String role = theSystemApp.validateCredentials(username, password);

            if (role == null) {
                throw new IllegalArgumentException("No user found!");
            }

            String token = createToken(username, role);

            SimpleEntry<String, String> JWT = new SimpleEntry<>("JWT", token); //wel een body!
            System.out.println("User found! Role: " + role);
            return Response.ok(JWT).build();
        }

        catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build(); //let op: geen body!
        }
    }

    private String createToken(String username, String role) {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        int accountID = theSystemApp.getAccountByName(username).getAccountID();

        return Jwts.builder()
                .setSubject(Integer.toString(accountID))
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
