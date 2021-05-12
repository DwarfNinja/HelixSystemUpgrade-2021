package helixsystemupgrade.setup;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("helixsystem")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("webservices");
    }
}
