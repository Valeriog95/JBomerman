/* Projects : JBomberman
 * Created 02/10/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Game;

import Controller.BombermanController;
import Controller.Menu.MainMenuController;
import Controller.Sounds.BombermanAudioSample;
import View.Menu.Frames.GameFinishMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Specific controller used in game finish window
 */
public class GameFinishController extends BombermanController {

    private final GameFinishMenu view;

    private class GameFinishAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            new MainMenuController();
        }
    }

    /**
     * Create new controller instance for finish game menu
     * @param result result of the game
     * @param actualPoints actual points reached
     * @param totalPoints total points reached
     * @param level actual level reached
     */
    public GameFinishController(String result,int actualPoints,int totalPoints, String level) {
        view = new GameFinishMenu(result,actualPoints,totalPoints,level);
        controllerInCharge = this;
        view.setActionsPerformed(this);
        audioManager.play(
                result.contains( "WIN" ) ? BombermanAudioSample.GAME_WINNER : BombermanAudioSample.RESULT_SCREEN
                );
    }

    /**
     * Get finish action listener
     * @return action listened instance
     */
    public ActionListener getFinishAction() { return new GameFinishAction(); }

    @Override
    public void repaintView() {
        view.repaint();
    }
}
