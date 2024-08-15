package redhat.summit2024.websockets;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;

@ServerEndpoint("/chat/display")
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
    public void onMessage(String message ) {
        if (message.equalsIgnoreCase("_ready_")) {
            LOG.info("display joined");
        } else {
            broadcast(message);
        }
    }

    private void broadcast(String message) {
 
        if (displaySession != null){

            displaySession.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
            
        } else {
            LOG.info(" No display joined - trying to send message " + message);
        }
        
    }

}