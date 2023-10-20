package Model.Game.Blocks;

/**
 * Block that can cross crossable Block
 */
public interface Crosser {
    /**
     * Get block that previously stayed in that position
     * @return previous block type
     */
    Block getPrevious();

    /**
     * Set provious block
     * @param blockCrossed block type
     */
    void setPrevious(Block blockCrossed);
}
