package redhat.summit2024.websockets;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;

@ServerEndpoint("/camera/view")
@ApplicationScoped
public class DisplaySocket {

    private static final Logger LOG = Logger.getLogger(DisplaySocket.class);

    Session displaySession = null;

    @OnOpen
    public void onOpen(Session session) {
        displaySession = session;
    }

    @OnClose
    public void onClose(Session session) {
        displaySession = null;
    }

    @OnError
    public void onError(Session session,  Throwable throwable) {
        displaySession = null;
        LOG.error("onError", throwable);
    }

    @OnMessage
    public void onMessage(String encodedImage ) {
        if (encodedImage.equalsIgnoreCase("_ready_")) {
            LOG.info("display joined");
        } else {
            broadcast(encodedImage);
        }
    }

    private void broadcast(String encodedImage) {
 
        if (displaySession != null){

            displaySession.getAsyncRemote().sendObject(encodedImage, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        } 
        
    }

}