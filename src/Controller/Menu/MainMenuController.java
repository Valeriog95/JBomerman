/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Menu;

import Controller.BombermanController;
import Controller.Sounds.BombermanAudioSample;
import View.Menu.Frames.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Specific controller used in Main Menu window
 */
public class MainMenuController extends BombermanController {

    private final MainMenu view;

    private class MainMenuAction implements ActionListener{
        private final String action;

        public MainMenuAction(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            view.dispose();

            switch (action) {
                case "START_GAME" -> new StartGameController();
                case "SELECT_PLAYER" -> new SelectPlayerController();
                case "CREATE_PLAYER" -> new CreatePlayerController();
                case "EXIT" -> BombermanController.audioManager.close();
                default -> throw new IllegalStateException("Unexpected MainMenu action value: " + action);
            }

        }
    }

    /**
     * Create new controller instance for main menu
     */
    public MainMenuController(){

        view = new MainMenu();
        controllerInCharge = this;
        view.setActionsPerformed(this);
        audioManager.play( BombermanAudioSample.MAIN_MENU );
    }

    /**
     * Get start game action listened
     * @return action listened instance
     */
    public ActionListener getStartGameAction(){ return new MainMenuAction("START_GAME"); }

    /**
     * Get select player action listened
     * @return action listened instance
     */
    public ActionListener getSelectPlayerAction(){ return new MainMenuAction("SELECT_PLAYER"); }

    /**
     * Get create player action listened
     * @return action listened instance
     */
    public ActionListener getCreatePlayerAction(){ return new MainMenuAction("CREATE_PLAYER"); }

    /**
     * Get exit action listened
     * @return action listened instance
     */
    public ActionListener getExitAction(){ return new MainMenuAction("EXIT"); }

    @Override
    public void repaintView() {
        view.repaint();
    }
}
