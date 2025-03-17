import javax.swing.*;
class Main{

    public static boolean gameOver = false;
    private static Timer mainTimer; // make them members of the Main class so that they can be referenced from outside.
    private static Timer pipesTimer;
    private static float score = 0; // will keep track of how many pipes are crossed.
    private static JFrame mainFrame;
    public static void main(String[] args){
        
        int width = 360;
        int height = 640;

        // set up the window for the game

        mainFrame = new JFrame("Flappy Bird");
        mainFrame.setSize(width,height);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartGame();
    }

    private static void StartGame(){

        // Add the environment to the scene
        Environment e = new Environment();
        mainFrame.add(e);
        mainFrame.pack();

        // start an infinite loop to update the game @60 Frames Per Second

        mainTimer = new Timer(1000/60,e); // 16.6 ms e.actionPerformed();
        mainTimer.start();

        // Set the input system

        InputManager i = new InputManager(); // creating an object of the InputManager in the Main method.
        e.addKeyListener(i); // set the input to work using the e
        e.setFocusable(true); // give permission to handle user interactions.
        
        // start an infinite loop to generate pipes regularly, lets say @ every 2 seconds a pair of pipes will be added
        PipeGenerator pipesGenerator = new PipeGenerator();
        pipesTimer = new Timer(1200,pipesGenerator); // at every 2 seconds, the timer will call pipesGenerator.actionPerformed()
        pipesTimer.start();
        mainFrame.pack();
        
        // set the frame as visible
        mainFrame.setVisible(true);
    }

    // calling this function will stop the timers.
    public static void StopTheTimers(){
        mainTimer.stop();
        pipesTimer.stop();
        gameOver = true;
    }

    public static void GivePoints(float points){
        score += points;
    }

    public static int GetScore(){
        return (int) score;
    }

    public static void RestartGame(){

        PipeGenerator.pipes.clear();
        gameOver = false;
        score = 0;
        mainTimer.restart();
        pipesTimer.restart();
    }

}