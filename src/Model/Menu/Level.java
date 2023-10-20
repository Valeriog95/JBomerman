package Model.Menu;

/**
 * Enumeration providing possible levels that can be reached by players,
 * with descriptive and elegant names associated with each level.
 */
public enum Level {
    /**
     * Represents the TRAINING_GROUNDS level, available for players with scores
     * between 0 and 100.
     */
    TRAINING_GROUNDS(0, 100),

    /**
     * Represents the EXPLODING_CITIES level, available for players with scores
     * between 101 and 500.
     */
    EXPLODING_CITIES(101, 500),

    /**
     * Represents the FROZEN_CAVES level, available for players with scores
     * between 501 and 1000.
     */
    FROZEN_CAVES(501, 1000),

    /**
     * Represents the DARK_FOREST level, available for players with scores
     * between 1001 and 2500.
     */
    DARK_FOREST(1001, 2500),

    /**
     * Represents the HAUNTED_MANSION level, available for players with scores
     * between 2501 and 5000.
     */
    HAUNTED_MANSION(2501, 5000),

    /**
     * Represents the GALACTIC_WAR_ZONE level, available for players with scores
     * between 5001 and 10000.
     */
    GALACTIC_WAR_ZONE(5001, 10000),

    /**
     * Represents the WILD_WEST_DUEL level, available for players with scores
     * between 10001 and 15000.
     */
    WILD_WEST_DUEL(10001, 15000),

    /**
     * Represents the ALIEN_INVASION level, available for players with scores
     * between 15001 and 25000.
     */
    ALIEN_INVASION(15001, 25000),

    /**
     * Represents the UNDERWATER_DEPTHS level, available for players with scores
     * between 25001 and 50000.
     */
    UNDERWATER_DEPTHS(25001, 50000),

    /**
     * Represents the EXPLODING_CHAMPION level, available for players with scores
     * above 50001.
     */
    EXPLODING_CHAMPION(50001, Integer.MAX_VALUE);

    private final int minScore; // minimum score required to attain this level
    private final int maxScore; // maximum score that can be attained at this level

    /**
     * Constructor initializing the minimum and maximum scores required to attain this level.
     *
     * @param minScore the starting score for this level
     * @param maxScore the maximum attainable score while on this level
     */
    Level(int minScore, int maxScore) {
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    /**
     * Returns the Level corresponding to the given player score.
     *
     * @param score the player's score
     * @return the Level at which the player currently stands
     */
    public static Level fromScore(int score) {
        for (Level level : Level.values()) {
            if (score >= level.minScore && score <= level.maxScore) {
                return level;
            }
        }
        // Returns the highest level if the player's score is greater than EXPLODING_CHAMPION's maxScore.
        return EXPLODING_CHAMPION;
    }
}