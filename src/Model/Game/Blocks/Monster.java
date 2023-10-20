package Model.Game.Blocks;

import Model.Game.Rules.Status;

/**
 *  Class Monster
 */
public class Monster extends Brick implements Crossable,Crosser{

    private static final int EXPLODED_POINTS = 25;
    private Block previous = new Blank();

    /**
     * Instantiates a new Monster.
     */
    public Monster() {}

    @Override
    public Block spawned() {
        return new Blank();
    }

    @Override
    public int getPoints() { return EXPLODED_POINTS; }

    @Override
    public Status destroy() { return super.destroy(); }

    @Override
    public Status crossed(Block crosser) {
        return crosser instanceof Player ? Status.LOST : Status.CONTINUE;
    }

    @Override
    public Block getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(Block previous) {

        this.previous = (previous instanceof Portal) || (previous instanceof PowerUp) ? previous : new Blank();
    }

    @Override
    public String toString() { return "Monster"; }


}
