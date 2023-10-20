package Model.Game;

import java.util.function.Function;

/**
 * Possible player moves, use this enum to get relative function for each move
 */
public enum Moves {
    /**
     * Move up moves
     */
    MOVE_UP(Coordinate::moveUp),
    /**
     * Move right moves
     */
    MOVE_RIGHT(Coordinate::moveRight),
    /**
     * Move down moves
     */
    MOVE_DOWN(Coordinate::moveDown),
    /**
     * Move left moves
     */
    MOVE_LEFT(Coordinate::moveLeft);

    private final Function<Coordinate, Coordinate> moveFunction;

    /**
     * Constructor of move
     * @param moveFunction function used to move
     */
    Moves(Function<Coordinate, Coordinate> moveFunction) {
        this.moveFunction = moveFunction;
    }

    /**
     * Get move function
     * @return move function
     */
    public Function<Coordinate, Coordinate> move() { return moveFunction; }
}
