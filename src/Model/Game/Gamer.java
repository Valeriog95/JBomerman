package Model.Game;

import Model.Game.Rules.Difficulty;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Main Model implementation of the game
 */
public class Gamer extends Observable{

    /* Singleton pattern stuffs */
    static private Gamer instance;

    private Gamer() {}

    /**
     * Get gamer instance
     * @return instance of the game
     */
    public static Gamer getInstance() { return instance == null ? instance = new Gamer() : instance; }

    // Gaming objects
    private static final int RESPAWN_MONSTER_TIME = 2 * 60 * 1000;  // 2 minutes to respawn
    private static final int MOVE_MONSTER_TIME = 2000;
    private static final int BOMB_FIRING_TIME = 2000;
    private static final int DELAY_TIME = 500;
    private static final int LAST_LEVEL = 6;
    private GamePlay gamePlay;
    private String respawnTime = "00:00";
    private int level = 1;
    private boolean isPaused = true;
    private boolean isStopped = true;

    Thread spawner;
    Thread mover;

    /* Thread Semaphore mechanism */

    private volatile boolean isLocked;

    private void lockThread(){
        while (isLocked) Thread.onSpinWait();
        isLocked = true;
    }
    private void unlockThread(){isLocked = false;}

    /** Cyclic respawn monster thread */
    private void respawnMonsters(){

        int counter = RESPAWN_MONSTER_TIME;

        while (!isStopped){

            respawnTime = String.format(
                    "%d:%d",
                    ((RESPAWN_MONSTER_TIME /1000) - (counter/1000)) / 60,
                    ((RESPAWN_MONSTER_TIME /1000) - (counter/1000)) % 60
            );

            try{ Thread.sleep(DELAY_TIME); }
            catch (InterruptedException e) { e.printStackTrace(); }

            if(!isPaused)
                counter-=DELAY_TIME;


            if (counter <= 0){
                lockThread();
                setChanged(); notifyObservers( gamePlay.getGameStatus());
                setChanged(); notifyObservers( gamePlay.respawnMonsters());
                unlockThread();
                counter = RESPAWN_MONSTER_TIME;
            }
        }
    }

    /** Cyclic move Monster thread */
    private void moveMonsters(){
        int counter = MOVE_MONSTER_TIME;

        while (!isStopped){

            try{ Thread.sleep(DELAY_TIME); }
            catch (InterruptedException e) { e.printStackTrace(); }

            if(!isPaused)
                counter-=DELAY_TIME;

            if (counter <= 0){
                lockThread();
                setChanged(); notifyObservers( gamePlay.getGameStatus());
                setChanged(); notifyObservers( gamePlay.moveMonsters());
                unlockThread();
                counter = MOVE_MONSTER_TIME;
            }
        }
    }

    private void pauseThreads(){ isPaused = true; }
    private void resumeThreads(){ isPaused = false; }
    private void startThreads(){

        spawner = new Thread( this::respawnMonsters );
        mover = new Thread( this::moveMonsters );
        isStopped = false;
        resumeThreads();
        spawner.start();
        mover.start();
    }

    /**
     * Stop whole thread execution
     */
    public void stopThreads(){ unlockThread();isStopped = true; }

    /* Gaming Operations */

    /**
     * Get actual game status, provided as a Map, keys are :
     * TimeToRespawn + Model.Game.GamePlay#getGameStatus()
     * @return map of possible game status
     */
    public HashMap<String,String> getGameStatus(){
        HashMap<String,String> status = gamePlay.getGameStatus();
        status.put("TimeToRespawn",respawnTime);
        return status;
    }

    /**
     * Pause game thread
     */
    public void pause(){
        pauseThreads();
    }

    /**
     * Resume game thread
     */
    public void resume(){
        resumeThreads();
    }

    /**
     * Stop this game
     * @return points reached in this game
     */
    public int stop(){
        stopThreads();
        return gamePlay.getPoints();
    }

    /**
     * Start a new game
     * @param diff difficult of the game
     */
    public void start(Difficulty diff){
        stopThreads();
        gamePlay = new GamePlay();
        setChanged();
        notifyObservers(gamePlay.startGame(diff));
        startThreads();
    }

    /**
     * Restart this game, used when a life is lost
     * @param diff difficult of the game
     */
    public void restart(Difficulty diff){
        stopThreads();
        int points = gamePlay.getPoints();
        try { Thread.sleep( DELAY_TIME * 2); }
        catch (InterruptedException e) {  throw new RuntimeException( e ); }
        setChanged();
        notifyObservers( gamePlay.startGame(diff));
        gamePlay.setPoints( points );

        startThreads();
    }

    /* Player operations */

    /**
     * Callback used to move player UP
     */
    public void movePlayer_up(){
        setChanged();
        notifyObservers( gamePlay.movePlayer(Moves.MOVE_UP));
        setChanged();
        notifyObservers(getGameStatus());
    }

    /**
     * Callback used to move player DOWN
     */
    public void movePlayer_down(){
        setChanged();
        notifyObservers( gamePlay.movePlayer(Moves.MOVE_DOWN));
        setChanged();
        notifyObservers(getGameStatus());
    }

    /**
     * Callback used to move player LEFT
     */
    public void movePlayer_left(){
        setChanged();
        notifyObservers( gamePlay.movePlayer(Moves.MOVE_LEFT));
        setChanged();
        notifyObservers(getGameStatus());
    }

    /**
     * Callback used to move player RIGHT
     */
    public void movePlayer_right(){
        setChanged();
        notifyObservers( gamePlay.movePlayer(Moves.MOVE_RIGHT));
        setChanged();
        notifyObservers(getGameStatus());
    }

    /**
     * Callback used to place bomb if available
     */
    public void placeBomb() {

        Coordinate bomb = gamePlay.getBomb();

        // If bomb, starts a thread that explode bomb over timer
        if (bomb != null){


            Thread bombThread = new Thread(
                () -> {
                    int counter = BOMB_FIRING_TIME;

                    while (!isStopped){

                        if(!isPaused){
                            try{ Thread.sleep(DELAY_TIME); }
                            catch (InterruptedException e) { e.printStackTrace(); }
                            counter-=DELAY_TIME;
                        }

                        if (counter == 0){

                            // Explode Bombs
                            lockThread();
                            setChanged(); notifyObservers( gamePlay.explodeBomb(bomb));
                            setChanged(); notifyObservers( new HashMap<>(Map.of("status", "BOMB_EXPLODED")) );
                            setChanged(); notifyObservers( getGameStatus() );
                            unlockThread();

                            // Release Explosion on Field
                            try{ Thread.sleep(2 * DELAY_TIME); }
                            catch (InterruptedException e) { e.printStackTrace(); }

                            // Remove explosion
                            lockThread();
                            setChanged(); notifyObservers( gamePlay.removeExplosions());
                            unlockThread();
                        }
                    }
                }
            );

            bombThread.start();
        }
    }

    /**
     * Reset game level
     */
    public void resetLevel() {
        level = 1;
    }

    /**
     * Increment current level
     * @return true if there is next level
     */
    public boolean incrementLevel() {
        if (++level == LAST_LEVEL)  {
            level = 1;
            return false;
        }

        return true;
    }

    /**
     * Get current level
     * @return the number of current level
     */
    public int getLevel(){
        return level;
    }
}
