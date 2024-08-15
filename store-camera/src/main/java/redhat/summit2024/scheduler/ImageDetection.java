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
            System.out.println(response.getProducts());
            System.out.println(response.getPricetag());

            if(response.getKnown().equals("true")){
                if(response.getPricetag().contains("RHEL")) {
                    detectedTshirt = "rhel";
                }else if(response.getPricetag().contains("Openshift")){
                    detectedTshirt = "openshift";
                }else if(response.getPricetag().contains("Ansible")){
                    detectedTshirt = "ansible";
                }else if(response.getPricetag().contains("Developer")){
                    detectedTshirt = "developer";
                }else if(response.getPricetag().contains("AI")){
                    detectedTshirt = "ai";
                }else{
                    detectedTshirt  = "unknown";
                }
            }

        } catch (Exception e){
            // don't really care - probaly just means the services isnt running yet
            e.printStackTrace();
        }
        return detectedTshirt;
    }
    
}


