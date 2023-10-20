package Model.Game.Rules;

/**
 * The enum View.Game status.
 */
public enum Status {
    /**
     * Win game status.
     */
    WIN,
    /**
     * Lose game status.
     */
    LOST,
    /**
     * Continue game status.
     */
    CONTINUE,
    /**
     * Pause game status.
     */
    PAUSE,
    /**
     * Lost game status. (used when lives are finished)
     */
    END,
    /**
     * Improve stats. (Used when powerUp is dropped)
     */
    IMPROVE;
}
