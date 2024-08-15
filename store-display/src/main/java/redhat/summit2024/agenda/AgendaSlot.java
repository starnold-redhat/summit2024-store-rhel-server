package redhat.summit2024.agenda;

import java.time.LocalTime;
import java.util.List;

public class AgendaSlot {
    
    String startTime;
    String endTime;
    List<AgendaItem> agendaItems;


    public AgendaSlot() {
    }
    public AgendaSlot(String startTime, String endTime, List<AgendaItem> agendaItems) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.agendaItems = agendaItems;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public List<AgendaItem> getAgendaItems() {
        return agendaItems;
    }
    public void setAgendaItems(List<AgendaItem> agendaItems) {
        this.agendaItems = agendaItems;
    }
    public boolean isInTheFuture() {
        LocalTime startTimeAsTime = LocalTime.parse(startTime);
        LocalTime timeNow = LocalTime.now();
        boolean isInTheFuture = false;
        if (timeNow.isBefore(startTimeAsTime)){
            isInTheFuture = true;
        }
        return isInTheFuture;
    }
    public String getMessage(String tag) {
        String message = null;
        for (AgendaItem agendaItem : agendaItems) {
            if (agendaItem.matches(tag)){
              message = agendaItem.getMessage(tag, startTime);
            }
        }
        return message;
    }

}
