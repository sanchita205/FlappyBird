import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Environment extends JPanel implements ActionListener {

    static int panelWidth = 360;
    static int panelHeight = 640;

    Image bg;

    Bird flappyBird;

    public static float birdVelocity = 0; // velocity becomes -10 whenever pressed space
    public static float gravity = 0.4f;
    int pipeVelocity = -5;

    public Environment() {

        setPreferredSize(new Dimension(panelWidth,panelHeight));
        setBackground(Color.GREEN);
        bg = new ImageIcon("./Assets/background.png").getImage();
        flappyBird = new Bird();
    }

    // i want to add the images to the canvas / sheet

    @Override public void paintComponent(Graphics g){

        super.paintComponent(g);
        
        // added the background
        g.drawImage(bg,0,0,panelWidth,panelHeight,null);
        
        // add the bird
        g.drawImage(flappyBird.GetImage(),flappyBird.x,flappyBird.y,flappyBird.width,flappyBird.height,null);

        // draw all the pipes in the array :
        for(int i = 0; i < PipeGenerator.pipes.size(); i++){
            Pipe p = PipeGenerator.pipes.get(i);
            
            // draw the pipe
            g.drawImage(p.GetImage(), p.x, p.y, p.width, p.height,null);
        }

        g.setFont(new Font("Calibri",Font.BOLD,25));
        g.setColor(Color.BLUE);
        g.drawString("score ", 10, 30);
        g.setColor(Color.YELLOW);
        g.drawString(Main.GetScore()+"", 65, 30);

        // if game is over :
        if(Main.gameOver){

            g.setFont(new Font("Arial",Font.BOLD,35));

            // tell gameover gur gover
            g.drawString("Gave Over bro!",panelWidth /2 - 100,panelHeight/2-50);
        }
    }
    
    // This is the method that will be invoked 60 times every second
    private void Update(){

        // update the velocity of the bird
        birdVelocity = birdVelocity + gravity; 

        // update the position of the bird
        flappyBird.y = flappyBird.y + (int) birdVelocity;

        if(flappyBird.y < 0){
            flappyBird.y = 0;
        }

        // if the bird has fallen down to the floor of the screen then game over
        if(flappyBird.y + flappyBird.height > panelHeight){
            
            Main.StopTheTimers();
            return;
        }

        // update the position of all the pipes in the array
        int size = PipeGenerator.pipes.size();
        for(int i = 0; i < size; i++){

            Pipe currentPipe = PipeGenerator.pipes.get(i);
            currentPipe.x = currentPipe.x + pipeVelocity;

            // check if the pipe has gone past the left side of the window. if so, then remove it! ( Optimization )
            if(currentPipe.x + currentPipe.width < 0){

                PipeGenerator.pipes.remove(i);
                size--;
                continue;
            }

            // check if the bird has past the pipe or not
            if(currentPipe.hasNotPast && currentPipe.x + currentPipe.width < flappyBird.x){

                currentPipe.hasNotPast = false;
                Main.GivePoints(0.5f);
            }

            if(Collison(flappyBird,currentPipe )){

                Main.StopTheTimers();
            }
        }

        // redraw the entire scene
        repaint();
    }

    // it will be called by the mainTimer;
    @Override public void actionPerformed(ActionEvent event){
        Update();
    }

    // Make a function to detect collision between a bird and a pipe :

    private boolean Collison(Bird b, Pipe p){

        boolean xInRange = (b.x + b.width >= p.x + 1 && b.x + 1 <= p.x + p.width);
        boolean yInRange = (b.y + b.height >= p.y + 1 && b.y + 1 <= p.y + p.height);
        return (xInRange && yInRange);
    }

}