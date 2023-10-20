/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View;

import Controller.BombermanController;

/**
 * Generic Bomberman view, it must perform BombermanController typed actions
 * @param <C> Super class of Bomberman controller
 */
public interface BombermanView <C extends BombermanController> {

    /**
     * Attach action to view from Bomberman Controller
     * @param controller instance of controller in charge
     */
     void setActionsPerformed(C controller);
}
