package Model.Game.Rules;

import Model.Game.Moves;
import Model.Game.PositionChanged;

import java.util.List;

/**
 * The interface Move player.
 */
public interface MovePlayer {
    /**
     * Move player on field by the specified move
     *
     * @param moves the moves
     * @return the list of position changed
     */
    List<PositionChanged> movePlayer(Moves moves);

}
