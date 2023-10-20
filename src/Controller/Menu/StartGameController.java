/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Menu;

import Controller.BombermanController;
import Controller.Game.GameController;
import Model.Game.Rules.Difficulty;
import View.Menu.Frames.SelectDifficultMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.Sounds.BombermanAudioSample.STAGE_INTRO;

/**
 * Specific controller used in Start game window
 */
public class StartGameController extends BombermanController {

    private final SelectDifficultMenu selectDifficultMenu;

    private class StartGameAction implements ActionListener{

        private final Difficulty difficulty;

        public StartGameAction(Difficulty difficulty) {
            this.difficulty = difficulty;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectDifficultMenu.dispose();
            new GameController(difficulty);
            audioManager.play( STAGE_INTRO );
        }
    }

    /**
     * Create new controller instance for start game menu
     */
    public StartGameController(){
        selectDifficultMenu = new SelectDifficultMenu();
        controllerInCharge = this;
        selectDifficultMenu.setActionsPerformed(this);

    }

    /**
     * Get start easy game action listened
     * @return action listened instance
     */
    public ActionListener getStartEasyGameAction(){
        return new StartGameAction(Difficulty.EASY);
    }

    /**
     * Get start medium game action listened
     * @return action listened instance
     */
    public ActionListener getStartMediumGameAction(){
        return new StartGameController.StartGameAction(Difficulty.MEDIUM);
    }

    /**
     * Get start hard game action listened
     * @return action listened instance
     */
    public ActionListener getStartHardGameAction(){
        return new StartGameController.StartGameAction(Difficulty.HARD);
    }

    @Override
    public void repaintView() {
        selectDifficultMenu.repaint();
    }
}
