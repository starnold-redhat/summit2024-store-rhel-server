package redhat.summit2024.scheduler;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ImageDetection {

    @RestClient
    ImageDetectionService imageDetectionService;

    public String checkImage(String image) {

        String detectedTshirt = "unknown";

        try{
            ImageFromCamera cameraImage = new ImageFromCamera();
            cameraImage.setImage(image);
            ImageDetectionResponse response = imageDetectionService.detectKnownTShirt(cameraImage);
            System.out.println(response.getKnown());
            System.out.println(response.getItem());

            if(response.getKnown().equals("true")){
                String itemcode = response.getItem();
                if ("rhel".equals(itemcode) ||
                    "ansible".equals(itemcode) ||
                    "partners".equals(itemcode) ||
                    "openshift".equals(itemcode) ||
                    "ai".equals(itemcode) ||
                    "kubernetes".equals(itemcode) ||
                    "rhel".equals(itemcode)){

                        detectedTshirt = response.getItem();
                    }
            }

        } catch (Exception e){
            // don't really care - probaly just means the services isnt running yet
            e.printStackTrace();
        }
        return detectedTshirt;
    }
    
}


