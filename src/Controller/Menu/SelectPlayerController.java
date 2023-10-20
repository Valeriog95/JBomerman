/* Projects : JBomberman
 * Created 02/10/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller.Menu;

import Controller.BombermanController;
import View.Menu.Frames.SelectPlayerMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Specific controller used in player selection window
 */
public class SelectPlayerController extends BombermanController {

    private final SelectPlayerMenu view;
    private class SelectPlayerAction implements ActionListener{
        private final String nickname;
        public SelectPlayerAction(String nickname) {
            this.nickname = nickname;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            model.getUser().setCurrentUser(nickname);
            view.dispose();
            new MainMenuController();
        }
    }

    /**
     * Create new controller instance for select player menu
     */
    public SelectPlayerController() {
        view = new SelectPlayerMenu();
        view.setUsers(model.getUser().getUsers());
        controllerInCharge = this;
        view.setActionsPerformed(this);
    }

    /**
     * Get select player action listened
     * @param nickname nickname of player
     * @return action listened instance
     */
    public ActionListener getSelectPlayerAction(String nickname){
        return new SelectPlayerAction(nickname);
    }

    @Override
    public void repaintView() {
        view.repaint();
    }
}
