package Main;
import Controller.Controller;

/**
 * JBomberman game, main class
 */
public class JBomberman {

    /** Indicates how many times the view will be repainted every second */
    private static final int FRAME_PER_SECOND = 10;

    /**
     * Entry point of the game
     * @param args command line arguments
     */
    public static void main(String[] args) {

        /* Create controller and delegate to it the entire program execution, the first state machine is inside
        *   Controller constructor method
        */
        Controller controller = new Controller();

        /* Infinite loop that repaint view */
        while(true){

            try {   Thread.sleep(1000 / FRAME_PER_SECOND); }
            catch (InterruptedException e) { throw new RuntimeException(e); }

            controller.repaintView();

        }
    }
}
