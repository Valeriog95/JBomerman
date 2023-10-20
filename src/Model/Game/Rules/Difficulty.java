package Model.Game.Rules;

/**
 * The enum Difficulty.
 */
public enum Difficulty {
    /**
     * Easy difficulty.
     */
    EASY(4, 10, 6),
    /**
     * Medium difficulty.
     */
    MEDIUM(6, 15, 6),
    /**
     * Hard difficulty.
     */
    HARD(6, 20, 6);

    private final int monsters;
    private final int bricks;
    private final int powerUps;

    Difficulty(int monsters, int bricks, int powerUps) {
        this.monsters = monsters;
        this.bricks = bricks;
        this.powerUps = powerUps;
    }

    /**
     * Gets monster number.
     *
     * @return the monsters
     */
    public int getMonsters() {
        return monsters;
    }

    /**
     * Gets brick number.
     *
     * @return the bricks
     */
    public int getBricks() {
        return bricks;
    }

    /**
     * Gets power up number.
     *
     * @return the power ups
     */
    public int getPowerUps() {
        return powerUps;
    }
}
