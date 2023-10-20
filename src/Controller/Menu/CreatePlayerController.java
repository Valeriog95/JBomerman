/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Menu;

import Controller.BombermanController;
import View.Menu.Frames.CreatePlayerMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Specific controller used in create player window
 */
public class CreatePlayerController extends BombermanController {

    private final CreatePlayerMenu view;

    /**
     * Action listener of create players button
     */
    private class CreatePlayerAction implements ActionListener {

        private final String avatar;

        public CreatePlayerAction(String avatar) {
            this.avatar = avatar;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(!model.getUser().addNewUser((CreatePlayerController.this.view).getNickname(),avatar))
                return;

            view.dispose();
            new MainMenuController();
        }
    }

    /**
     * Create new controller for create player menu
     */
    public CreatePlayerController() {
        view = new CreatePlayerMenu();
        controllerInCharge = this;
        view.setActionsPerformed(this);
    }

    /**
     * Get create red player action listened
     * @return action listened instance
     */
    public ActionListener getCreateRedPlayerAction(){return new CreatePlayerAction("Red");}

    /**
     * Get create blue player action listened
     * @return action listened instance
     */
    public ActionListener getCreateBluePlayerAction(){return new CreatePlayerAction("Blue");}

    /**
     * Get create black player action listened
     * @return action listened instance
     */
    public ActionListener getCreateBlackPlayerAction(){return new CreatePlayerAction("Black");}

    /**
     * Get create white player action listened
     * @return action listened instance
     */
    public ActionListener getCreateWhitePlayerAction(){return new CreatePlayerAction("White");}

    @Override
    public void repaintView() {
        view.repaint();
    }
}
