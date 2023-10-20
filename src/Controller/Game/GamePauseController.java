/* Projects : JBomberman
 * Created 02/10/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Game;

import Controller.BombermanController;
import Controller.Sounds.BombermanAudioSample;
import Model.Menu.User;
import View.GameView;
import View.Menu.Frames.GamePauseMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Specific controller used in game paused window
 */
public class GamePauseController extends BombermanController {

    private final GameView gameView;
    private final GameController gameController;
    private final GamePauseMenu view;

    private class GamePauseAction implements ActionListener{
        private final String action;

        public GamePauseAction(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (action) {
                case "EXIT" -> {
                    gameView.dispose();
                    view.dispose();
                    int points = model.getGamer().stop();
                    model.getUser().lost(points);
                    User user = model.getUser().getCurrentUser();
                    new GameFinishController(
                            user.getNickname() + " ... LOST!",
                            points,
                            user.getStats().getAllScores(),
                            user.getLevel()
                    );
                }
                case "RESUME" -> {
                    view.dispose();
                    model.getGamer().resume();
                    controllerInCharge = gameController;
                }
                default -> throw new IllegalStateException("Unexpected Game Pause action value: " + action);
            }
        }
    }

    /**
     * Create new controller instance for game pause menu
     * @param gameView instance of game view
     */
    public GamePauseController(GameView gameView) {
        this.gameView = gameView;
        view = new GamePauseMenu();
        model.getGamer().pause();
        gameController = (GameController) controllerInCharge;
        controllerInCharge = this;
        view.setActionsPerformed(this);
        audioManager.play( BombermanAudioSample.PAUSE );
    }

    /**
     * Get resume game action listened
     * @return action listened instance
     */
    public ActionListener getResumeAction() { return new GamePauseAction("RESUME"); }

    /**
     * Get exit from game action listened
     * @return action listened instance
     */
    public ActionListener getExitAction() { return new GamePauseAction("EXIT"); }

    @Override
    public void repaintView() {
        view.repaint();
    }
}
