import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class PipeGenerator implements ActionListener{
    // step I : this class will be responsible for managing and creating the pipes into the scene (game)

    // step III : lets keep to images for future references.
    static Image pipe_upImage = new ImageIcon("./Assets/pipe_upper.png").getImage();
    static Image pipe_downImage = new ImageIcon("./Assets/pipe_down.png").getImage();
    static Random random = new Random(4); // seed pseudo random number

    // step II : lets first create an array , that will store the pipes.
    public static ArrayList<Pipe> pipes = new ArrayList<>();

    public static void AddAPairOfPipes(){

        // it will create an up pipe and a down pipe.
        // then those pipes will be added to the list.

        Pipe upPipe = new Pipe(pipe_upImage);
        Pipe downPipe = new Pipe(pipe_downImage);

        int additionalHeight = random.nextInt(8* upPipe.height / 10);
        upPipe.y = 0 - upPipe.height / 10 - additionalHeight;
        downPipe.y = upPipe.y + upPipe.height + 150;

        // step : IV : add the pipes to the list
        pipes.add(upPipe);
        pipes.add(downPipe);

        // lets set up the heights of the newly created pipes first:
    }

    @Override public void actionPerformed(ActionEvent event){
        AddAPairOfPipes();
    }
}