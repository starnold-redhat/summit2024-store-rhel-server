package redhat.summit2024.scheduler;


import org.jboss.logging.Logger;

public class ImageFromCamera {

    private static final Logger LOG = Logger.getLogger(ImageFromCamera.class);
    private String image;

    public ImageFromCamera() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   
}
