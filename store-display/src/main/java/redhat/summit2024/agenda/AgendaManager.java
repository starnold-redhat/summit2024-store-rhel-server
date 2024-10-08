package redhat.summit2024.agenda;

import java.io.File;
import java.time.LocalTime;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AgendaManager {
    
    private static final Logger LOG = Logger.getLogger(AgendaManager.class);
    private List<AgendaSlot> agenda = null;

    @ConfigProperty(name = "store.display.timeout")
    int secondsForDisplayToTimeout;
    
    LocalTime displayTimeout = LocalTime.now();
    String cachedMessage = "";



    AgendaManager (){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
          File agendaFile = new File("agenda.json");
          
          agenda = objectMapper.readValue(agendaFile, new TypeReference<List<AgendaSlot>>(){});
        } catch (Exception e) {
          LOG.error("Problems reading agenda file");
        }
    }

    public String getMessage(String tag) {
        String message = "choose a t-shirt, and hold it up in front of the camera";
        if (shouldSendCachedMessage(tag)){
          message = cachedMessage;
        } else if  ("unknown".equals(tag)) {
          message = "choose a t-shirt, and hold it up in front of the camera";
        } else {
          // we need to check for whether the tag matches an agenda item
          boolean agendaFound = false;
          for (AgendaSlot agendaSlot : agenda) {
              if (agendaSlot.isInTheFuture() && !agendaFound){
                  String returnMessage = agendaSlot.getMessage(tag);
                  if (returnMessage != null){
                      agendaFound = true;
                      message = returnMessage; 
                  }
              }
          }
          if (!agendaFound){
            if ("summitconnect".equals(tag)){
              message = "Hope you enjoyed the demo";
            } else {
              message = "Sorry, there are no more sessions for " + tag + ".  See you at the final session at 4.30";
            }
          }
          message=prettifyTag(tag)+"$"+message;
          //because we've successfully spotted a tshirt - then cache the message
          cachedMessage = message;
          displayTimeout = LocalTime.now().plusSeconds(secondsForDisplayToTimeout);
        }
      return message;
    }
    private boolean shouldSendCachedMessage(String tag) {
      boolean shouldSendCachedMessage = false;
      LocalTime timeNow = LocalTime.now();
      if (timeNow.isBefore(displayTimeout)){
        shouldSendCachedMessage = true;
      }
      return shouldSendCachedMessage;
    }

    String prettifyTag(String tag){
      String prettyTag = tag;
      if ("rhel".equals(tag)){
        prettyTag = "RHEL";
      } else if ("ai".equals(tag)){
        prettyTag="AI";
      } else if ("openshift".equals(tag)){
        prettyTag="OpenShift";
      } else{
        prettyTag = tag.substring(0,1).toUpperCase() + tag.substring(1).toLowerCase();
      }
      return prettyTag;
   }
}

    
