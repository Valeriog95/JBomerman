package Model.Game.Blocks;

import Model.Game.Rules.Status;

/**
 * The type Portal.
 */
public class Portal extends CrossableBlock{

    boolean isActive = false;

    /**
     * Instantiates a new Portal.
     */
    public Portal() {}

    /**
     * Set portal to active, in order to win this match
     * @param active true if game is winnable else false
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public Status crossed(Block crosser) { return isActive ? Status.WIN : Status.CONTINUE ; }

    @Override
    public String toString() { return "Portal"; }
}
