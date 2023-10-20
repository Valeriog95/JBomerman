package Model.Game.Rules;

import Model.Game.Blocks.Blank;
import Model.Game.Blocks.Block;
import Model.Game.Blocks.Crossable;
import Model.Game.Blocks.Monster;
import Model.Game.Coordinate;
import Model.Game.PositionChanged;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface Move monsters.
 */
public interface MonsterMover {

    /**
     * Move monsters that are on the field
     * @param monster coordinate of the monster
     * @return the list of position changed
     */
    List<PositionChanged> moveMonster(Coordinate monster);
}
