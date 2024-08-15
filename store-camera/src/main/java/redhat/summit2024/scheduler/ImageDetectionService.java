package redhat.summit2024.scheduler;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/detection")
@RegisterRestClient(configKey = "imagedetection-api")
public interface ImageDetectionService {
    
    @POST
    ImageDetectionResponse detectKnownTShirt(ImageFromCamera image);

}
