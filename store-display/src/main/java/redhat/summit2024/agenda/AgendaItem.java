package redhat.summit2024.agenda;

import java.util.List;

public class AgendaItem {
    String title;
    List<String> tags;
    String room;
    
    
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public AgendaItem() {
    }

    public AgendaItem(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }
    
    public String getTitle() {
        return title;
    }
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public boolean matches(String tag) {
        boolean matches = false;
        if (tags.contains(tag)){
            matches = true;
        }
        return matches;
    }

    public String getMessage(String tag,  String startTime) {
        String message = null;
        if (matches(tag)){
            message = "We love " + tag + " too! \nYour next " + tag + " session is: \n'" + title + "' in " + room + " at " + startTime + ".  \nHave a great session!";
        }

        return message;
    }

    String prettifyTag(String tag){
       String prettyTag = tag;
       if ("rhel".equals(tag)){
         prettyTag = "RHEL";
       } else if ("ai".equals(tag)){
         prettyTag="AI";
       }
       else{
         prettyTag = tag.substring(0,1).toUpperCase() + tag.substring(1).toLowerCase();
       }
       return prettyTag;
    }


}
