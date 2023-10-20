package Model.Game.Blocks;

import Model.Game.Rules.Status;

/**
 * Block that can be destroyed
 */
public interface Destroyable{

    /**
     * Destroy block.
     *
     * @return status of game
     */
    Status destroy();

    /**
     * Block spawned after explosion
     * @return block type spawned
     */
    Block spawned();

    /**
     * Get points from exploded block
     * @return number of points dropped
     */
    int getPoints();
}
