/* Projects : JBomberman
 * Created 30/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package Model;

import Model.Game.Gamer;

/**
 * Main Bomberman model, it only provides the two singleton implementation of models
 */
public final class BombermanModel {

    /**
     * Get gamer instance
     * @return gamer instanc
     */
    public Gamer getGamer(){ return Gamer.getInstance(); }

    /**
     * Get user instance
     * @return gamer instance
     */
    public UserHandler getUser() { return UserHandler.getInstance(); }

    /**
     * Generic bomberman model constructor
     */
    public BombermanModel() {
    }
}
