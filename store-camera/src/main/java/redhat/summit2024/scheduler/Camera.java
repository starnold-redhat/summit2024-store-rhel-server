package redhat.summit2024.scheduler;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;

import com.github.sarxos.webcam.Webcam;

import jakarta.enterprise.context.ApplicationScoped;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

@ApplicationScoped
public class Camera {

    
    private static final Logger LOG = Logger.getLogger(Camera.class);
    public String getImage() {
        // call web camera function
        String image = null;
        try {
            Webcam webcam = Webcam.getDefault();
            webcam.open();
            BufferedImage captured_image = webcam.getImage();
            image = imgToBase64String(captured_image, "png");
            
        }catch(Exception e){
            System.out.println("Unable to take a picture - camera probably not connected");
        }
        //encode the ensuing file
        //return the string
        return image;
    }

    public String encodeFile(File imageFile){
        String image="";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(imageFile);
            image = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.out.println("Error encoding the image from a file");
        }
        return image;
    }
    
    public String imgToBase64String(final RenderedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (final OutputStream b64os = Base64.getEncoder().wrap(os)) {
            ImageIO.write(img, formatName, b64os);
        } catch (final IOException ioe) {
           System.out.println("Error in imgToBase64String encoding image");;
        }
        return os.toString();
    }
    
}