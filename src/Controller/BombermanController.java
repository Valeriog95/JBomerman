/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Controller;

import Controller.Sounds.AudioManager;
import Model.BombermanModel;

/**
 * Generic Bomberman Controller, it contains model (only one instance provided with singleton pattern) and one view
 * that can be repainted
 */
public abstract class BombermanController{
    /**
     * Model in charge (always the same one for singleton pattern stuff
     */
    protected static BombermanModel model;
    /**
     * Controller in charge
     */
    protected static BombermanController controllerInCharge;
    /**
     * Audio manager instance used to play bomberman tracks
     */
    protected static AudioManager audioManager;

    /**
     * Useful method that repaints view when called, main Controller must repaint controller in charge
     */
    abstract public void repaintView();

}
