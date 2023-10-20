package Model.Game.Blocks;

import Model.Game.Moves;

/**
 * Enum providing possible directions that can be moved in a game,
 * with meaningful and expressive names for each direction.
 */
public enum Direction {
    ContinueUp,     // move continuously upwards
    Up,             // move up by one step
    ContinueDown,   // move continuously downwards
    Down,           // move down by one step
    ContinueRight,  // move continuously rightwards
    Right,          // move right by one step
    ContinueLeft,   // move continuously leftwards
    Left,           // move left by one step
    Center;         // stay in the center

    /**
     * Given the move and the number of steps, return the direction type.
     *
     * @param move the direction to move in
     * @param step the number of steps to move in that direction
     * @return the appropriate Direction for the move
     */
    public static Direction getDirectionFromMove(Moves move, int step) {
        return switch (move) {
            case MOVE_UP -> step > 1 ? ContinueUp : Up;           // return ContinueUp if more than 1 step, else return Up
            case MOVE_DOWN -> step > 1 ? ContinueDown : Down;     // return ContinueDown if more than 1 step, else return Down
            case MOVE_RIGHT -> step > 1 ? ContinueRight : Right;  // return ContinueRight if more than 1 step, else return Right
            case MOVE_LEFT -> step > 1 ? ContinueLeft : Left;     // return ContinueLeft if more than 1 step, else return Left
        };
    }
}
