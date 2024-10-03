package redhat.summit2024.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import redhat.summit2024.agenda.AgendaManager;
import redhat.summit2024.websockets.DisplaySocket;

@Path("/identified")
public class IdentificationEndpoint {

    @Inject
    DisplaySocket display;

    @Inject
    AgendaManager agendaManager;
    
    @Path("{tag}")
    @GET
    public String identifiedTShirt(String tag) {
        
        System.out.println("identified " + tag);
        String message = agendaManager.getMessage(tag);
       
        display.onMessage(message);
        return "ok";
    }
}
