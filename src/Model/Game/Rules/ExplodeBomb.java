package Model.Game.Rules;

import Model.Game.Coordinate;
import Model.Game.PositionChanged;

import java.util.List;

/**
 * Provide an interface to bomb exploded, send map of exploded fields
 */
public interface ExplodeBomb {
    /**
     * Explode bomb at the coordinate passed return position changed on field.
     *
     * @param bomb the bomb coordinate
     * @return the list of position changed
     */
    List<PositionChanged> explodeBomb(Coordinate bomb);

    /**
     * Implementation used when explosion must be removed
     * @return the list of position changed
     */
    List<PositionChanged> removeExplosions();

}
