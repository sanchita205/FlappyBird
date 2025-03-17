import java.awt.event.*;
public class InputManager implements KeyListener{ // inheriting an interface means signing a contract

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(Main.gameOver){

            Main.RestartGame();
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){ // if the button that is pressed by the user is Space button,

            Environment.birdVelocity = -9;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}