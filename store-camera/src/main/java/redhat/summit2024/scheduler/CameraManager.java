package redhat.summit2024.scheduler;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.scheduler.Scheduled;


@ApplicationScoped
public class CameraManager {

    @Inject
    Camera camera;

    @Inject
    ImageDetection imageDetection;

    @Inject
    StoreDisplay storeDisplay;

    @Scheduled(every = "1s")
    void checkCameraImage() {
        String b64Image = camera.getImage();

        String tag = imageDetection.checkImage(b64Image);

        storeDisplay.displayMessageForImage(tag);
        
    }
}
