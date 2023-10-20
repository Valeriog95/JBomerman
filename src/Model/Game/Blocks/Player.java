package Model.Game.Blocks;

import Model.Game.Rules.Status;

/**
 * Class Player
 */
public class Player extends CrossableBlock implements Destroyable,Crosser {

    private Block previous = new Blank();

    /**
     * Constructor of class Player
     */
    public Player() {}

    @Override
    public Status destroy() { return Status.LOST; }

    @Override
    public Block spawned() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public Status crossed(Block crosser) {
        return (crosser instanceof Monster) ? Status.LOST : Status.CONTINUE ;
    }

    @Override
    public Block getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(Block previous) {
        this.previous = (previous instanceof Portal || previous instanceof Bomb) ? previous : new Blank();
    }

    @Override
    public String toString() { return "Player"; }
}
