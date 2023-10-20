package Model.Game.Blocks;

import Model.Game.Rules.Status;

import java.util.Random;

/**
 * The type Power up.
 */
public class PowerUp extends CrossableBlock{

    private final PowerUpType type;

    /**
     * Instantiates a new Power up.
     */
    public PowerUp() {
        Random rand = new Random();
        type = PowerUpType.values()[rand.nextInt(PowerUpType.values().length)];
    }

    /**
     * Gets power up type.
     *
     * @return the type
     */
    public PowerUpType getType() {
        return type;
    }

    @Override
    public Status crossed(Block crosser) {
        return (crosser instanceof Player) ? Status.IMPROVE : Status.CONTINUE;
    }

    @Override
    public String toString() { return "PowerUp" + type.name(); }
}
