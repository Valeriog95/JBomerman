/* Projects : JBomberman
 * Created 24/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Game;

import Controller.BombermanController;
import Controller.Sounds.BombermanAudioSample;
import Model.Game.Rules.Difficulty;
import Model.Menu.User;
import View.GameView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * Specific controller used when gaming
 */
public class GameController extends BombermanController implements KeyListener, Observer {

    private final Difficulty difficulty;
    private GameView gameView;

    private void startGame(){

        // Remove old view and set new one
        if (gameView != null) gameView.dispose();
        gameView = new GameView();
        gameView.setCurrentLevel( model.getGamer().getLevel() );
        gameView.setPlayerAvatar(model.getUser().getCurrentUser().getAvatar());
        gameView.addKeyListener(this);

        audioManager.play( BombermanAudioSample.STAGE_INTRO );
        model.getGamer().start(difficulty);
    }

    private void continueGame(){

        // Playing next level
        if (model.getGamer().incrementLevel()){
            model.getGamer().stopThreads();
            try{ Thread.sleep(500); }
            catch (InterruptedException e) { e.printStackTrace(); }
            startGame();
        }

        // Win game
        else {
            User user = model.getUser().getCurrentUser();
            int points = model.getGamer().stop();
            model.getUser().win(points);
            gameView.dispose();
            new GameFinishController(
                user.getNickname() + " ... WIN!",
                points,
                user.getStats().getAllScores(),
                user.getLevel()
            );
        }

    }

    /**
     * Create new controller for game instance
     * @param difficulty difficult of game played
     */
    public GameController(Difficulty difficulty) {
        this.difficulty = difficulty;
        controllerInCharge = this;
        model.getGamer().addObserver(this);
        model.getGamer().resetLevel();
        startGame();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
//                audioManager.play( BombermanAudioSample.WALKING );
                model.getGamer().movePlayer_up();
            }
            case KeyEvent.VK_A -> {
//                audioManager.play( BombermanAudioSample.WALKING );
                model.getGamer().movePlayer_left();
            }
            case KeyEvent.VK_S -> {
//                audioManager.play( BombermanAudioSample.WALKING );
                model.getGamer().movePlayer_down();
            }
            case KeyEvent.VK_D -> {
//                audioManager.play( BombermanAudioSample.WALKING );
                model.getGamer().movePlayer_right();
            }
            case KeyEvent.VK_SPACE -> {
                audioManager.play( BombermanAudioSample.PLACE_BOMB );
                model.getGamer().placeBomb();
            }
            case KeyEvent.VK_ESCAPE ->
                new GamePauseController(gameView);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void repaintView() {
        if (gameView != null) gameView.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {

        // Positions..
        if (arg instanceof List && ((List<?>) arg).size() > 0)
            gameView.setPositions(arg);

        // Game stats
        else if (arg instanceof HashMap<?,?>){
            HashMap<String, String> status = (HashMap<String, String>) arg;
            gameView.setStats(status);
            switch (status.get("status")){

                // Play this level again, one life is lost...
                case "LOST" -> {
                    audioManager.play( BombermanAudioSample.PLAYER_DIES );
                    gameView.killPlayer();
                    model.getGamer().stopThreads();
                    try{ Thread.sleep(500); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                    model.getGamer().restart(difficulty);
                }

                // Play next level if it's not the last
                case "WIN" -> {
                    audioManager.play( BombermanAudioSample.MATCH_WINNER );
                    continueGame();
                }
                // Lost whole lives, return to main menu
                case "END" -> {
                    model.getGamer().stop();
                    User user = model.getUser().getCurrentUser();
                    int points = model.getGamer().stop();
                    model.getUser().lost(points);
                    new GameFinishController(
                        user.getNickname() + " ... LOST!",
                        points,
                        user.getStats().getAllScores(),
                        user.getLevel()
                    );
                }

                case "IMPROVE" -> audioManager.play( BombermanAudioSample.POWER_UP_GET );
                case "BOMB_EXPLODED" -> audioManager.play( BombermanAudioSample.BOMB_EXPLODES );
                case "MONSTER_DIES" -> audioManager.play( BombermanAudioSample.MONSTER_DIES );

            }

        }

    }

}
