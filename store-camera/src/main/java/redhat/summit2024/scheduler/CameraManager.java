package redhat.summit2024.scheduler;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import redhat.summit2024.websockets.DisplaySocket;
import io.quarkus.scheduler.Scheduled;


@ApplicationScoped
public class CameraManager {

    @Inject
    Camera camera;

    @Inject
    ImageDetection imageDetection;

    @Inject
    StoreDisplay storeDisplay;

    @Inject
    DisplaySocket display;

    @Scheduled(every = "1s")
    void checkCameraImage() {
        String b64Image = camera.getImage();

        if (b64Image !=null){

            display.onMessage(b64Image);
    
            String tag = imageDetection.checkImage(b64Image);
    
            storeDisplay.displayMessageForImage(tag);
        } else {
            System.out.println("Encoded image is null");
        }
        
    }
}
