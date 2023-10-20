/* Projects : JBomberman
 * Created 22/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller;

import Controller.Menu.MainMenuController;
import Controller.Sounds.AudioManager;
import Model.BombermanModel;

/**
 * Main controller of Bomberman Game
 */
public class Controller extends BombermanController{

    /**
     * Constructor of controller, it creates:
     * 1. model instantiate for the first time,
     * 2. audio player instance
     * 2. first view showed (Main Menu view)
     */
    public Controller() {
        model = new BombermanModel();
        audioManager = new AudioManager();
        controllerInCharge = new MainMenuController();
    }

    @Override
    public final void repaintView() {
        controllerInCharge.repaintView();
    }
}
