package Model.Game.Blocks;

import Model.Game.Rules.Status;

/**
 * The interface Crossable.
 */
public interface Crossable{
    /**
     * Cross block.
     *
     * @param crosser the crosser block
     * @return the block type returned by crossing
     */
    Status crossed (Block crosser);

}
