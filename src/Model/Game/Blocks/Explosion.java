package Model.Game.Blocks;

/**
 * Explosion block class, spawned after bomb explosion
 */
public class Explosion extends Block{

    private final Direction direction;
    private final Block held;

    /**
     * Constructor of class explosion
     * @param direction direction of the explosion
     * @param held Block type before explosion
     */
    public Explosion(Direction direction,Block held) {
        this.direction = direction;
        this.held = held;
    }

    /**
     * Get held block
     * @return block type
     */
    public Block getHeld() {
        return held;
    }

    @Override
    public String toString() {
        return "Explosion" + direction.name();
    }

}
