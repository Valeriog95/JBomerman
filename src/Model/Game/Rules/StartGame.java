package Model.Game.Rules;

import java.util.List;

/**
 * The interface Start game.
 */
public interface StartGame {

    /**
     * Start game, return the matrix of blocks as String of block name generated
     *
     * @param diff the difficulty, used to set blocks
     * @return the matrix of blocks
     */
    List<List<String>> startGame(Difficulty  diff);

}
