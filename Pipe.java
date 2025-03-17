
import java.awt.Image;


/*
    This class will be used to define pipe objects.
    UnLike the bird class defines a single bird, we will have a lot of pipes here.
 */

public class Pipe{
    
    int x = Environment.panelWidth;
    int y = 0;

    int width = 64;
    int height = 512;
    boolean hasNotPast = true; // tells wheather the bird has crossed the pipe or not

    private final Image img;

    public Pipe(Image img) {
        this.img = img;
    }
    
    public Image GetImage(){
        return img;
    }
}