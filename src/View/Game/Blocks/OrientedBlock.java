/* Projects : JBomberman
 * Created 22/09/2023 for Metodologie di Programmazione course at La Sapienza University of Rome
 *
 * Copyright (c) Valerio Gregori <gregorivalerio@gmail.com>
 *
 */
package View.Game.Blocks;

import Model.Game.Coordinate;

/**
 * Class that defines a generic oriented block (it must be also animated for obvious reasons)
 */
public abstract class OrientedBlock extends AnimatedBlock{
    /** Current orientation of block */
    protected String currentDirection = "center";
    /** Current position of block */
    protected Coordinate currentPosition;

    /**
     * Set current position of the block
     * @param currentPosition Current coordinate
     */
    public void setCurrentPosition(Coordinate currentPosition) {
        currentDirection = this.currentPosition == null ? "center" : Coordinate.getDirection(this.currentPosition,currentPosition);
        this.currentPosition = currentPosition;
    }

    /**
     * Generic oriented block constructor
     */
    public OrientedBlock() {
    }
}
