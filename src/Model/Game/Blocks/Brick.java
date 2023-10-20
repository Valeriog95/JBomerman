package Model.Game.Blocks;

import Model.Game.Rules.Status;

/**
 * The type Brick.
 */
public class Brick extends Block implements Destroyable {

    private static final int EXPLODED_POINTS = 100;
    private boolean powerUp = false;
    private boolean portal = false;

    /**
     * Instantiates a new Brick.
     */
    public Brick() {}

    /**
     * Sets power up.
     *
     * @param powerUp the power up
     */
    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    /**
     * Sets portal.
     *
     * @param portal the portal
     */
    public void setPortal(boolean portal) {
        this.portal = portal;
    }

    @Override
    public Block spawned(){
        if (portal)     return new Portal();
        if (powerUp)    return new PowerUp();
        return new Blank();
    }

    @Override
    public int getPoints() { return EXPLODED_POINTS; }

    @Override
    public Status destroy() { return Status.CONTINUE; }

    @Override
    public String toString() { return "Brick"; }
}

