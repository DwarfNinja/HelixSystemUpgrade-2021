package helixsystemupgrade.setup;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;



@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("helixsystemupgrade.webservices, helixsystemupgrade.security");
        register(RolesAllowedDynamicFeature.class);
    }
}
