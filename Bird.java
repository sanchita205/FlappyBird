
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bird{
    int x = 360 / 8;
    int y = 640 / 2;

    int width = 50;
    int height = 32;

    private final Image img; // using the image as a property of the bird 

    public Bird() {

        ImageIcon icon = new ImageIcon("./Assets/bird.png");
        img = icon.getImage();
    }

    public Image GetImage(){
        return img;
    }
    
}