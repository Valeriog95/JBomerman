package Model.Game.Blocks;

import Model.Game.Rules.Status;

/**
 * Blank Block
 */
public class Blank extends CrossableBlock {

    /**
     * Constructor of class Blank
     */
    public Blank() {}

    @Override
    public Status crossed(Block crosser) {
        return Status.CONTINUE;
    }

    @Override
    public String toString() { return "Blank"; }
}
