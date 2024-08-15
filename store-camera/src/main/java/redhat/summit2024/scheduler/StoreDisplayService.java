package redhat.summit2024.scheduler;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/identified")
@RegisterRestClient(configKey = "storedisplay-api")
public interface StoreDisplayService {
    
    @GET
    @Path("/{tag}")
    String getByTag(@PathParam("tag") String tag);

}
