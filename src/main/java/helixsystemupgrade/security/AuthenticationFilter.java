package helixsystemupgrade.security;

import helixsystemupgrade.model.Account;
import helixsystemupgrade.model.SystemApp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    SystemApp theSystemApp = SystemApp.getTheSystemApp();

    @Override
    public void filter(ContainerRequestContext requestContext)  {
        boolean isSecure = requestContext.getSecurityContext().isSecure();
        // Gets scheme, HTTP or HTTPS
        String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
        // Users are treated as guests, unless a valid JWT is provided
        MySecurityContext securityContext = new MySecurityContext(null, scheme);

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring("Bearer".length()).trim();

            try {
                //Validate the token
                JwtParser jwtParser = Jwts.parser().setSigningKey(AuthenticationResource.key);
                Claims claims = jwtParser.parseClaimsJws(token).getBody();

                String accountID = claims.getSubject();

                Account account = theSystemApp.getAccountByID(Integer.parseInt(accountID));

                securityContext = new MySecurityContext(account, scheme);

                System.out.println("Valid JWT, processing as " + account.getAccountRole() + "!");
            }
            catch (JwtException | IllegalArgumentException e) {
                System.out.println("Invalid JWT, processing as guest!");
            }
        }
        requestContext.setSecurityContext(securityContext);
    }
}
