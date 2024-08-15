package redhat.summit2024.scheduler;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class StoreDisplay {

    @RestClient 
    StoreDisplayService storeDisplayService;

    public void displayMessageForImage(String tag) {
        
        try{
            storeDisplayService.getByTag(tag);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
